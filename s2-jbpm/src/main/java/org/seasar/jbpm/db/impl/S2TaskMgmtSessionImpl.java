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
package org.seasar.jbpm.db.impl;

import java.util.Arrays;
import java.util.List;

import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.seasar.jbpm.db.S2TaskMgmtSession;

/**
 * @author glad
 */
public class S2TaskMgmtSessionImpl implements S2TaskMgmtSession {

    final TaskMgmtSession taskMgmtSession;

    public S2TaskMgmtSessionImpl(TaskMgmtSession taskMgmtSession) {
        this.taskMgmtSession = taskMgmtSession;
    }

    public TaskMgmtSession getTaskMgmtSession() {
        return taskMgmtSession;
    }

    // ==== task instances

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstances(String actorId) {
        return taskMgmtSession.findTaskInstances(actorId);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstances(String... actorIds) {
        return taskMgmtSession.findTaskInstances(actorIds);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstances(List<?> actorIds) {
        return taskMgmtSession.findTaskInstances(actorIds);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findPooledTaskInstances(String actorId) {
        return taskMgmtSession.findPooledTaskInstances(actorId);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findPooledTaskInstances(String... actorIds) {
        return taskMgmtSession.findPooledTaskInstances(Arrays.asList(actorIds));
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findPooledTaskInstances(List<?> actorIds) {
        return taskMgmtSession.findPooledTaskInstances(actorIds);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstancesByProcessInstance(
            ProcessInstance processInstance) {
        return taskMgmtSession.findTaskInstancesByProcessInstance(
                processInstance);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstancesByToken(long tokenId) {
        return taskMgmtSession.findTaskInstancesByToken(tokenId);
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstancesByIds(String... taskInstanceIds) {
        return taskMgmtSession.findTaskInstancesByIds(
                Arrays.asList(taskInstanceIds));
    }

    @SuppressWarnings("unchecked")
    public List<TaskInstance> findTaskInstancesByIds(List<?> taskInstanceIds) {
        return taskMgmtSession.findTaskInstancesByIds(taskInstanceIds);
    }

    public TaskInstance getTaskInstance(long taskInstanceId) {
        return taskMgmtSession.getTaskInstance(taskInstanceId);
    }

    public TaskInstance loadTaskInstance(long taskInstanceId) {
        return taskMgmtSession.loadTaskInstance(taskInstanceId);
    }

}
