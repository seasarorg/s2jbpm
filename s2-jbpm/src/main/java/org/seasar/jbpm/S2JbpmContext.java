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
package org.seasar.jbpm;

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
import org.seasar.jbpm.db.S2ContextSession;
import org.seasar.jbpm.db.S2GraphSession;
import org.seasar.jbpm.db.S2JobSession;
import org.seasar.jbpm.db.S2LoggingSession;
import org.seasar.jbpm.db.S2TaskMgmtSession;

/**
 * @author glad
 */
public interface S2JbpmContext {

    JbpmContext getJbpmContext();

    void close();

    // ==== process definitions

    void deployProcessDefinition(ProcessDefinition processDefinition);

    // ==== process instances

    ProcessInstance newProcessInstance(String processDefinitionName);

    ProcessInstance newProcessInstanceForUpdate(String processDefinitionName);

    ProcessInstance getProcessInstance(long processInstanceId);

    ProcessInstance getProcessInstance(
            ProcessDefinition processDefinition, String key);

    ProcessInstance getProcessInstanceForUpdate(long processInstanceId);

    ProcessInstance getProcessInstanceForUpdate(
            ProcessDefinition processDefinition, String key);

    ProcessInstance loadProcessInstance(long processInstanceId);

    ProcessInstance loadProcessInstance(
            ProcessDefinition processDefinition, String key);

    ProcessInstance loadProcessInstanceForUpdate(long processInstanceId);

    ProcessInstance loadProcessInstanceForUpdate(
            ProcessDefinition processDefinition, String key);

    void save(ProcessInstance processInstance);

    // ==== tokens

    Token getToken(long tokenId);

    Token getTokenForUpdate(long tokenId);

    Token loadToken(long tokenId);

    Token loadTokenForUpdate(long tokenId);

    void save(Token token);

    // ==== task instances

    List<TaskInstance> getTaskList();

    List<TaskInstance> getTaskList(String actorId);

    List<TaskInstance> getGroupTaskList(String... actorIds);

    List<TaskInstance> getGroupTaskList(List<?> actorIds);

    TaskInstance getTaskInstance(long taskInstanceId);

    TaskInstance getTaskInstanceForUpdate(long taskInstanceId);

    TaskInstance loadTaskInstance(long taskInstanceId);

    TaskInstance loadTaskInstanceForUpdate(long taskInstanceId);

    void save(TaskInstance taskInstance);

    // ==== transactions

    void setRollbackOnly();

    // ==== services

    Services getServices();

    ServiceFactory getServiceFactory(String name);

    ObjectFactory getObjectFactory();

    // ==== jbpm database access sessions

    GraphSession getGraphSession();

    ContextSession getContextSession();

    TaskMgmtSession getTaskMgmtSession();

    JobSession getJobSession();

    LoggingSession getLoggingSession();

    S2GraphSession getS2GraphSession();

    S2ContextSession getS2ContextSession();

    S2TaskMgmtSession getS2TaskMgmtSession();

    S2JobSession getS2JobSession();

    S2LoggingSession getS2LoggingSession();

    // ==== authentication methods

    String getActorId();

    void setActorId(String actorId);

}
