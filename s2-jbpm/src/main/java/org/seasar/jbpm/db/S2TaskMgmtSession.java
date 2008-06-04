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
package org.seasar.jbpm.db;

import java.util.List;

import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author glad
 */
public interface S2TaskMgmtSession {

    TaskMgmtSession getTaskMgmtSession();

    // ==== task instances

    List<TaskInstance> findTaskInstances(String actorId);

    List<TaskInstance> findTaskInstances(String... actorIds);

    List<TaskInstance> findTaskInstances(List<?> actorIds);

    List<TaskInstance> findPooledTaskInstances(String actorId);

    List<TaskInstance> findPooledTaskInstances(String... actorIds);

    List<TaskInstance> findPooledTaskInstances(List<?> actorIds);

    List<TaskInstance> findTaskInstancesByProcessInstance(
            ProcessInstance processInstance);

    List<TaskInstance> findTaskInstancesByToken(long tokenId);

    List<TaskInstance> findTaskInstancesByIds(String... taskInstanceIds);

    List<TaskInstance> findTaskInstancesByIds(List<?> taskInstanceIds);

    TaskInstance getTaskInstance(long taskInstanceId);

    TaskInstance loadTaskInstance(long taskInstanceId);

}
