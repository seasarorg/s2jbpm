package org.seasar.jbpm.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.hibernate3.S2SessionFactory;
import org.seasar.jbpm.S2JbpmConfiguration;

public class S2JbpmContextImplTest extends S2TestCase {

    S2JbpmConfiguration jbpmConfiguration;

    S2SessionFactory sessionFactory;

    protected void setUp() throws Exception {
        include("s2jbpm.dicon");
    }

    S2JbpmContextImpl createS2JbpmContext() {
        return (S2JbpmContextImpl) jbpmConfiguration.createS2JbpmContext();
    }

    Session getSession() {
        return sessionFactory.getSession();
    }

    @SuppressWarnings("unchecked")
    <T> T load(Class<T> clazz, Serializable id) {
        return (T) getSession().load(clazz, id);
    }

    public void testJbpmContextTx() {
        JbpmContext internal = jbpmConfiguration.createJbpmContext();
        try {
            S2JbpmContextImpl jbpmContext = new S2JbpmContextImpl(internal);
            assertSame(internal, jbpmContext.getJbpmContext());
        } finally {
            internal.close();
        }
    }

    public void testProcessDefinitionTx() {
        ProcessDefinition processDefinition = deployProcessDefinition();
        assertSame(processDefinition,
                load(ProcessDefinition.class, processDefinition.getId()));
    }

    ProcessDefinition deployProcessDefinition() {
        ProcessDefinition processDefinition = readProcessDefinition();
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            jbpmContext.deployProcessDefinition(processDefinition);
            return processDefinition;
        } finally {
            jbpmContext.close();
        }
    }

    ProcessDefinition readProcessDefinition() {
        return ProcessDefinition.parseXmlInputStream(
                S2JbpmContextImplTest.class
                        .getResourceAsStream("processdefinition.xml"));
    }

    public void testProcessInstanceTx() {
        deployProcessDefinition();
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            ProcessInstance processInstance
                    = jbpmContext.newProcessInstance("S2JbpmContextImplTest");
            long processInstanceId = processInstance.getId();
            assertSame(processInstance,
                    jbpmContext.getProcessInstance(processInstanceId));
            assertSame(processInstance,
                    jbpmContext.getProcessInstanceForUpdate(processInstanceId));
            assertSame(processInstance,
                    jbpmContext.loadProcessInstance(processInstanceId));
            assertSame(processInstance,
                    jbpmContext.loadProcessInstanceForUpdate(processInstanceId));
            assertSame(processInstance,
                    load(ProcessInstance.class, processInstanceId));
            assertEquals("start", getCurrentStateName(processInstance));
            
            processInstance.signal();
            assertEquals("sayHello", getCurrentStateName(processInstance));
            
            processInstance.signal();
            assertEquals("end", getCurrentStateName(processInstance));
        } finally {
            jbpmContext.close();
        }
    }

    String getCurrentStateName(ProcessInstance processInstance) {
        return processInstance.getRootToken().getNode().getName();
    }

    public void testTokenTx() {
        deployProcessDefinition();
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            ProcessInstance processInstance
                    = jbpmContext.newProcessInstance("S2JbpmContextImplTest");
            Token token = processInstance.getRootToken();
            long tokenId = token.getId();
            assertSame(token, jbpmContext.getToken(tokenId));
            assertSame(token, jbpmContext.getTokenForUpdate(tokenId));
            assertSame(token, jbpmContext.loadToken(tokenId));
            assertSame(token, jbpmContext.loadTokenForUpdate(tokenId));
            assertSame(token, load(Token.class, tokenId));
            assertEquals("start", token.getNode().getName());
            
            token.signal();
            assertEquals("sayHello", token.getNode().getName());
            
            token.signal();
            assertEquals("end", token.getNode().getName());
        } finally {
            jbpmContext.close();
        }
    }

    public void testTaskInstanceTx() {
        deployProcessDefinition();
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            ProcessInstance processInstance
                    = jbpmContext.newProcessInstance("S2JbpmContextImplTest");
            assertEquals("start", getCurrentStateName(processInstance));
            assertEquals(0, jbpmContext.getTaskList("hello-01").size());
            
            processInstance.signal();
            assertEquals("sayHello", getCurrentStateName(processInstance));
            List<TaskInstance> taskList = jbpmContext.getTaskList("hello-01");
            assertEquals(1, taskList.size());
            
            TaskInstance taskInstance = taskList.get(0);
            assertEquals("sayHello", taskInstance.getName());
            long taskInstanceId = taskInstance.getId();
            assertSame(taskInstance,
                    jbpmContext.getTaskInstance(taskInstanceId));
            assertSame(taskInstance,
                    jbpmContext.getTaskInstanceForUpdate(taskInstanceId));
            assertSame(taskInstance,
                    jbpmContext.loadTaskInstance(taskInstanceId));
            assertSame(taskInstance,
                    jbpmContext.loadTaskInstanceForUpdate(taskInstanceId));
            assertSame(taskInstance, load(TaskInstance.class, taskInstanceId));
            
            taskInstance.start();
            assertEquals("sayHello", getCurrentStateName(processInstance));
            assertEquals(1, jbpmContext.getTaskList("hello-01").size());
            
            taskInstance.end();
            assertEquals("end", getCurrentStateName(processInstance));
            assertEquals(0, jbpmContext.getTaskList("hello-01").size());
        } finally {
            jbpmContext.close();
        }
    }

    public void testServicesTx() {
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            JbpmContext internal = jbpmContext.getJbpmContext();
            assertSame(internal.getServices(), jbpmContext.getServices());
        } finally {
            jbpmContext.close();
        }
    }

    public void testObjectFactoryTx() {
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            JbpmContext internal = jbpmContext.getJbpmContext();
            assertSame(internal.getObjectFactory(),
                    jbpmContext.getObjectFactory());
        } finally {
            jbpmContext.close();
        }
    }

    public void testSessionsTx() {
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            JbpmContext internal = jbpmContext.getJbpmContext();
            assertSame(internal.getGraphSession(),
                    jbpmContext.getGraphSession());
            assertSame(internal.getContextSession(),
                    jbpmContext.getContextSession());
            assertSame(internal.getTaskMgmtSession(),
                    jbpmContext.getTaskMgmtSession());
            assertSame(internal.getJobSession(),
                    jbpmContext.getJobSession());
            assertSame(internal.getLoggingSession(),
                    jbpmContext.getLoggingSession());
            
            assertSame(internal.getGraphSession(),
                    jbpmContext.getS2GraphSession().getGraphSession());
            assertSame(internal.getContextSession(),
                    jbpmContext.getS2ContextSession().getContextSession());
            assertSame(internal.getTaskMgmtSession(),
                    jbpmContext.getS2TaskMgmtSession().getTaskMgmtSession());
            assertSame(internal.getJobSession(),
                    jbpmContext.getS2JobSession().getJobSession());
            assertSame(internal.getLoggingSession(),
                    jbpmContext.getS2LoggingSession().getLoggingSession());
        } finally {
            jbpmContext.close();
        }
    }

    public void testActorIdTx() {
        S2JbpmContextImpl jbpmContext = createS2JbpmContext();
        try {
            JbpmContext internal = jbpmContext.getJbpmContext();
            assertNull(internal.getActorId());
            assertNull(jbpmContext.getActorId());
            jbpmContext.setActorId("hello-01");
            assertEquals("hello-01", internal.getActorId());
            assertEquals("hello-01", jbpmContext.getActorId());
            jbpmContext.setActorId(null);
            assertNull(internal.getActorId());
            assertNull(jbpmContext.getActorId());
        } finally {
            jbpmContext.close();
        }
    }

}
