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

import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 * @author glad
 */
public interface S2GraphSession {

    GraphSession getGraphSession();

    // ==== process definitions

    List<ProcessDefinition> findAllProcessDefinitions();

    List<ProcessDefinition> findLatestProcessDefinitions();

    List<ProcessDefinition> findAllProcessDefinitionVersions(String name);

    ProcessDefinition findLatestProcessDefinition(String name);

    ProcessDefinition findProcessDefinition(String name, int version);

    ProcessDefinition getProcessDefinition(long processDefinitionId);

    ProcessDefinition loadProcessDefinition(long processDefinitionId);

    void saveProcessDefinition(ProcessDefinition processDefinition);

    void deployProcessDefinition(ProcessDefinition processDefinition);

    void deleteProcessDefinition(long processDefinitionId);

    void deleteProcessDefinition(ProcessDefinition processDefinition);

    // ==== process instances

    List<ProcessInstance> findProcessInstances(long processDefinitionId);

    ProcessInstance getProcessInstance(long processInstanceId);

    ProcessInstance getProcessInstance(
            ProcessDefinition processDefinition, String key);

    ProcessInstance loadProcessInstance(long processInstanceId);

    ProcessInstance loadProcessInstance(
            ProcessDefinition processDefinition, String key);

    void lockProcessInstance(long processInstanceId);

    void lockProcessInstance(ProcessInstance processInstance);

    void deleteProcessInstance(long processInstanceId);

    void deleteProcessInstance(ProcessInstance processInstance);

    void deleteProcessInstance(ProcessInstance processInstance,
            boolean includeTasks, boolean includeJobs);

    // ==== tokens

    Token getToken(long tokenId);

    Token loadToken(long tokenId);

}
