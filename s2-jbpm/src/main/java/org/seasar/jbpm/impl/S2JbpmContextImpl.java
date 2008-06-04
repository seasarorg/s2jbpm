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

import java.util.Arrays;
import java.util.List;

import org.jbpm.JbpmContext;
import org.jbpm.configuration.ObjectFactory;
import org.jbpm.db.ContextSession;
import org.jbpm.db.GraphSession;
import org.jbpm.db.JobSession;
import org.jbpm.db.LoggingSession;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.svc.ServiceFactory;
import org.jbpm.svc.Services;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.seasar.jbpm.S2JbpmContext;
import org.seasar.jbpm.db.S2ContextSession;
import org.seasar.jbpm.db.S2GraphSession;
import org.seasar.jbpm.db.S2JobSession;
import org.seasar.jbpm.db.S2LoggingSession;
import org.seasar.jbpm.db.S2TaskMgmtSession;
import org.seasar.jbpm.db.impl.S2ContextSessionImpl;
import org.seasar.jbpm.db.impl.S2GraphSessionImpl;
import org.seasar.jbpm.db.impl.S2JobSessionImpl;
import org.seasar.jbpm.db.impl.S2LoggingSessionImpl;
import org.seasar.jbpm.db.impl.S2TaskMgmtSessionImpl;

/**
 * @author glad
 */
public class S2JbpmContextImpl implements S2JbpmContext {

    final JbpmContext jbpmContext;

    public S2JbpmContextImpl(JbpmContext jbpmContext) {
        this.jbpmContext = jbpmContext;
    }

    public JbpmContext getJbpmContext() {
        return jbpmContext;
    }

    public void close() {
        jbpmContext.close();
    }

    // ==== process definitions

    public void deployProcessDefinition(ProcessDefinition processDefinition) {
        jbpmContext.deployProcessDefinition(processDefinition);
    }

    // ==== process instances

    public ProcessInstance newProcessInstance(String processDefinitionName) {
        return jbpmContext.newProcessInstance(processDefinitionName);
    }

    public ProcessInstance newProcessInstanceForUpdate(
            String processDefinitionName) {
        return jbpmContext.newProcessInstanceForUpdate(processDefinitionName);
    }

    public ProcessInstance getProcessInstance(long processInstanceId) {
        return jbpmContext.getProcessInstance(processInstanceId);
    }

    public ProcessInstance getProcessInstance(
            ProcessDefinition processDefinition, String key) {
        return jbpmContext.getProcessInstance(processDefinition, key);
    }

    public ProcessInstance getProcessInstanceForUpdate(
            long processInstanceId) {
        return jbpmContext.getProcessInstanceForUpdate(processInstanceId);
    }

    public ProcessInstance getProcessInstanceForUpdate(
            ProcessDefinition processDefinition, String key) {
        return jbpmContext.getProcessInstanceForUpdate(processDefinition, key);
    }

    public ProcessInstance loadProcessInstance(long processInstanceId) {
        return jbpmContext.loadProcessInstance(processInstanceId);
    }

    public ProcessInstance loadProcessInstance(
            ProcessDefinition processDefinition, String key) {
        return jbpmContext.loadProcessInstance(processDefinition, key);
    }

    public ProcessInstance loadProcessInstanceForUpdate(
            long processInstanceId) {
        return jbpmContext.loadProcessInstanceForUpdate(processInstanceId);
    }

    public ProcessInstance loadProcessInstanceForUpdate(
            ProcessDefinition processDefinition, String key) {
        return jbpmContext.loadProcessInstanceForUpdate(processDefinition, key);
    }

    public void save(ProcessInstance processInstance) {
        jbpmContext.save(processInstance);
    }

    // ==== tokens

    public Token getToken(long tokenId) {
        return jbpmContext.getToken(tokenId);
    }

    public Token getTokenForUpdate(long tokenId) {
        return jbpmContext.getTokenForUpdate(tokenId);
    }

    public Token loadToken(long tokenId) {
        return jbpmContext.loadToken(tokenId);
    }

    public Token loadTokenForUpdate(long tokenId) {
        return jbpmContext.loadTokenForUpdate(tokenId);
    }

    public void save(Token token) {
        jbpmContext.save(token);
    }

    // ==== task instances

    @SuppressWarnings("unchecked")
    public List<TaskInstance> getTaskList() {
        return jbpmContext.getTaskList();
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> getTaskList(String actorId) {
        return jbpmContext.getTaskList(actorId);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> getGroupTaskList(String... actorIds) {
        return jbpmContext.getGroupTaskList(Arrays.asList(actorIds));
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> getGroupTaskList(List<?> actorIds) {
        return jbpmContext.getGroupTaskList(actorIds);
    }

    public TaskInstance getTaskInstance(long taskInstanceId) {
        return jbpmContext.getTaskInstance(taskInstanceId);
    }

    public TaskInstance getTaskInstanceForUpdate(long taskInstanceId) {
        return jbpmContext.getTaskInstanceForUpdate(taskInstanceId);
    }

    public TaskInstance loadTaskInstance(long taskInstanceId) {
        return jbpmContext.loadTaskInstance(taskInstanceId);
    }

    public TaskInstance loadTaskInstanceForUpdate(long taskInstanceId) {
        return jbpmContext.loadTaskInstanceForUpdate(taskInstanceId);
    }

    public void save(TaskInstance taskInstance) {
        jbpmContext.save(taskInstance);
    }

    // ==== transactions

    public void setRollbackOnly() {
        jbpmContext.setRollbackOnly();
    }

    // ==== services

    public Services getServices() {
        return jbpmContext.getServices();
    }

    public ServiceFactory getServiceFactory(String name) {
        return jbpmContext.getServiceFactory(name);
    }

    public ObjectFactory getObjectFactory() {
        return jbpmContext.getObjectFactory();
    }

    // ==== jbpm database access sessions

    public GraphSession getGraphSession() {
        return jbpmContext.getGraphSession();
    }

    public ContextSession getContextSession() {
        return jbpmContext.getContextSession();
    }

    public TaskMgmtSession getTaskMgmtSession() {
        return jbpmContext.getTaskMgmtSession();
    }

    public JobSession getJobSession() {
        return jbpmContext.getJobSession();
    }

    public LoggingSession getLoggingSession() {
        return jbpmContext.getLoggingSession();
    }

    public S2GraphSession getS2GraphSession() {
        return new S2GraphSessionImpl(getGraphSession());
    }

    public S2ContextSession getS2ContextSession() {
        return new S2ContextSessionImpl(getContextSession());
    }

    public S2TaskMgmtSession getS2TaskMgmtSession() {
        return new S2TaskMgmtSessionImpl(getTaskMgmtSession());
    }

    public S2JobSession getS2JobSession() {
        return new S2JobSessionImpl(getJobSession());
    }

    public S2LoggingSession getS2LoggingSession() {
        return new S2LoggingSessionImpl(getLoggingSession());
    }

    // ==== authentication methods

    public String getActorId() {
        return jbpmContext.getActorId();
    }

    public void setActorId(String actorId) {
        jbpmContext.setActorId(actorId);
    }

}
