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
package org.seasar.jbpm.impl;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.seasar.hibernate3.S2SessionFactory;
import org.seasar.jbpm.S2JbpmConfiguration;
import org.seasar.jbpm.S2JbpmContext;

/**
 * @author glad
 */
public class S2JbpmConfigurationImpl implements S2JbpmConfiguration {

    String resourceName;

    volatile JbpmConfiguration jbpmConfiguration = null;

    S2SessionFactory sessionFactory;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public JbpmConfiguration getJbpmConfiguration() {
        if (jbpmConfiguration == null) {
            // JbpmConfiguration#getInstance() は内部で同期化されている。
            jbpmConfiguration = JbpmConfiguration.getInstance(resourceName);
        }
        return jbpmConfiguration;
    }

    public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
        this.jbpmConfiguration = jbpmConfiguration;
    }

    public S2SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(S2SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public JbpmContext createJbpmContext() {
        JbpmContext jbpmContext
                = getJbpmConfiguration().createJbpmContext();
        customize(jbpmContext);
        return jbpmContext;
    }

    public JbpmContext createJbpmContext(String name) {
        JbpmContext jbpmContext
                = getJbpmConfiguration().createJbpmContext(name);
        customize(jbpmContext);
        return jbpmContext;
    }

    protected void customize(JbpmContext jbpmContext) {
        if (sessionFactory != null) {
            jbpmContext.setSession(sessionFactory.getSession());
        }
    }

    public S2JbpmContext createS2JbpmContext() {
        return createS2JbpmContext(createJbpmContext());
    }

    public S2JbpmContext createS2JbpmContext(String name) {
        return createS2JbpmContext(createJbpmContext(name));
    }

    protected S2JbpmContext createS2JbpmContext(JbpmContext jbpmContext) {
        return new S2JbpmContextImpl(jbpmContext);
    }

}
