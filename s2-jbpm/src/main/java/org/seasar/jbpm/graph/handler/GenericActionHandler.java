/*
 * Copyright 2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.jbpm.graph.handler;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.jbpm.graph.exe.ExecutionContext;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author glad
 */
public class GenericActionHandler extends AbstractActionHandler {

    private static final long serialVersionUID = 767985852469764505L;

    static final Object[] NO_ARGS = new Object[0];

    String componentName;
    String componentClass;
    String methodName;
    List<String> parameterNames;
    String resultName = "result";
    String transitionName;

    volatile transient Object component = null;
    volatile transient Method method = null;

    public void execute(ExecutionContext executionContext) {
        if (component == null) {
            component = getComponent();
        }
        Object[] args = getArguments(executionContext);
        if (method == null || !isInvocable(method, args)) {
            method = getMethod(component, args);
        }
        Object result = MethodUtil.invoke(method, component, args);
        setResult(executionContext, result);
        leaveNode(executionContext);
    }

    Object getComponent() {
        if (componentName != null) {
            return getComponent(componentName);
        } else {
            Class<?> clazz = ClassUtil.forName(componentClass);
            return getComponent(clazz);
        }
    }

    Object[] getArguments(ExecutionContext executionContext) {
        if (parameterNames == null || parameterNames.isEmpty()) {
            return NO_ARGS;
        }
        int size = parameterNames.size();
        Object[] args = new Object[size];
        for (int i = 0; i < size; ++i) {
            String name = parameterNames.get(i);
            args[i] = executionContext.getVariable(name);
        }
        return args;
    }

    Method getMethod(Object component, Object[] args) {
        Class<?> clazz = component.getClass();
        for (Method method : clazz.getMethods()) {
            if (isTargetMethod(method, args)) {
                return method;
            }
        }
        Class<?>[] paramTypes = getParameterTypes(args);
        throw new NoSuchMethodRuntimeException(
                clazz, methodName, paramTypes, null);
    }

    Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; ++i) {
            if (args[i] != null) {
                types[i] = args[i].getClass();
            } else {
                types[i] = Object.class;
            }
        }
        return types;
    }

    boolean isTargetMethod(Method method, Object[] args) {
        int mod = method.getModifiers();
        if (!Modifier.isPublic(mod) || Modifier.isStatic(mod)) {
            return false;
        }
        String name = method.getName();
        if (!name.equals(methodName)) {
            return false;
        }
        if (!isInvocable(method, args)) {
            return false;
        }
        return true;
    }

    boolean isInvocable(Method method, Object[] args) {
        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length != args.length) {
            return false;
        }
        for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) continue;
            Class<?> actualType = args[i].getClass();
            if (!ClassUtil.isAssignableFrom(paramTypes[i], actualType)) {
                return false;
            }
        }
        return true;
    }

    void setResult(ExecutionContext executionContext, Object result) {
        if (resultName != null) {
            executionContext.setVariable(resultName, result);
        }
    }

    void leaveNode(ExecutionContext executionContext) {
        if (transitionName == null) {
            executionContext.leaveNode();
        } else {
            executionContext.leaveNode(transitionName);
        }
    }

}
