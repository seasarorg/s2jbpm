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

import java.util.List;

import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.seasar.jbpm.db.S2GraphSession;

/**
 * @author glad
 */
public class S2GraphSessionImpl implements S2GraphSession {

    final GraphSession graphSession;

    public S2GraphSessionImpl(GraphSession graphSession) {
        this.graphSession = graphSession;
    }

    public GraphSession getGraphSession() {
        return graphSession;
    }

    // ==== process definitions

    @SuppressWarnings("unchecked")
    public List<ProcessDefinition> findAllProcessDefinitions() {
        return graphSession.findAllProcessDefinitions();
    }

    @SuppressWarnings("unchecked")
    public List<ProcessDefinition> findLatestProcessDefinitions() {
        return graphSession.findLatestProcessDefinitions();
    }

    @SuppressWarnings("unchecked")
    public List<ProcessDefinition> findAllProcessDefinitionVersions(
            String name) {
        return graphSession.findAllProcessDefinitionVersions(name);
    }

    public ProcessDefinition findLatestProcessDefinition(String name) {
        return graphSession.findLatestProcessDefinition(name);
    }

    public ProcessDefinition findProcessDefinition(String name, int version) {
        return graphSession.findProcessDefinition(name, version);
    }

    public ProcessDefinition getProcessDefinition(long processDefinitionId) {
        return graphSession.getProcessDefinition(processDefinitionId);
    }

    public ProcessDefinition loadProcessDefinition(long processDefinitionId) {
        return graphSession.loadProcessDefinition(processDefinitionId);
    }

    public void saveProcessDefinition(ProcessDefinition processDefinition) {
        graphSession.saveProcessDefinition(processDefinition);
    }

    public void deployProcessDefinition(ProcessDefinition processDefinition) {
        graphSession.deployProcessDefinition(processDefinition);
    }

    public void deleteProcessDefinition(long processDefinitionId) {
        graphSession.deleteProcessDefinition(processDefinitionId);
    }

    public void deleteProcessDefinition(ProcessDefinition processDefinition) {
        graphSession.deleteProcessDefinition(processDefinition);
    }

    // ==== process instances

    @SuppressWarnings("unchecked")
    public List<ProcessInstance> findProcessInstances(
            long processDefinitionId) {
        return graphSession.findProcessInstances(processDefinitionId);
    }

    public ProcessInstance getProcessInstance(long processInstanceId) {
        return graphSession.getProcessInstance(processInstanceId);
    }

    public ProcessInstance getProcessInstance(
            ProcessDefinition processDefinition, String key) {
        return graphSession.getProcessInstance(processDefinition, key);
    }

    public ProcessInstance loadProcessInstance(long processInstanceId) {
        return graphSession.loadProcessInstance(processInstanceId);
    }

    public ProcessInstance loadProcessInstance(
            ProcessDefinition processDefinition, String key) {
        return graphSession.loadProcessInstance(processDefinition, key);
    }

    public void lockProcessInstance(long processInstanceId) {
        graphSession.lockProcessInstance(processInstanceId);
    }

    public void lockProcessInstance(ProcessInstance processInstance) {
        graphSession.lockProcessInstance(processInstance);
    }

    public void deleteProcessInstance(long processInstanceId) {
        graphSession.deleteProcessInstance(processInstanceId);
    }

    public void deleteProcessInstance(ProcessInstance processInstance) {
        graphSession.deleteProcessInstance(processInstance);
    }

    public void deleteProcessInstance(ProcessInstance processInstance,
            boolean includeTasks, boolean includeJobs) {
        graphSession.deleteProcessInstance(
                processInstance, includeTasks, includeJobs);
    }

    // ==== tokens

    public Token getToken(long tokenId) {
        return graphSession.getToken(tokenId);
    }

    public Token loadToken(long tokenId) {
        return graphSession.loadToken(tokenId);
    }

}
