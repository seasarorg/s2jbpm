package org.seasar.jbpm.settings;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * jBPM の動作確認。
 * 
 * @author glad
 */
public class JbpmTest extends S2TestCase {

    static Log log = LogFactory.getLog(JbpmTest.class);

    protected void setUp() throws Exception {
        include("j2ee.dicon");
    }

    protected JbpmContext createJbpmContext() {
        return JbpmConfiguration.getInstance().createJbpmContext();
    }

    protected Session getSession() {
        return JbpmConfiguration.getInstance()
                .getCurrentJbpmContext().getSession();
    }

    @SuppressWarnings("unchecked")
    <T> T load(Class<T> clazz, Serializable id) {
        return (T) getSession().load(clazz, id);
    }

    public void testJbpmContextTx() {
        deployProcessDefinition();
        newProcessInstance();
        start();
        sayHello();
    }

    void deployProcessDefinition() {
        log.debug("<<< deployProcessDefinition >>>");
        ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
                "<process-definition name='JbpmTest'>" +
                "  <start-state name='start'>" +
                "    <transition to='sayHello'/>" +
                "  </start-state>" +
                "  <task-node name='sayHello'>" +
                "    <task name='sayHello'>" +
                "      <assignment actor-id='hello-01'/>" +
                "    </task>" +
                "    <transition to='end'/>" +
                "  </task-node>" +
                "  <end-state name='end'/>" +
                "</process-definition>");
        
        JbpmContext jbpmContext = createJbpmContext();
        try {
            jbpmContext.deployProcessDefinition(processDefinition);
            assertProcessDefinition(jbpmContext, processDefinition);
        } finally {
            jbpmContext.close();
        }
    }

    void newProcessInstance() {
        log.debug("<<< newProcessInstance >>>");
        JbpmContext jbpmContext = createJbpmContext();
        try {
            ProcessInstance processInstance
                    = jbpmContext.newProcessInstance("JbpmTest");
            jbpmContext.save(processInstance);
            assertProcessInstance(jbpmContext, processInstance);
            assertStartState(processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    void start() {
        log.debug("<<< start >>>");
        JbpmContext jbpmContext = createJbpmContext();
        try {
            ProcessInstance processInstance
                    = getHelloProcessInstance(jbpmContext);
            assertProcessInstance(jbpmContext, processInstance);
            assertStartState(processInstance);
            
            processInstance.signal();
            jbpmContext.save(processInstance);
            assertProcessInstance(jbpmContext, processInstance);
            assertNode("sayHello", processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    void sayHello() {
        log.debug("<<< sayHello >>>");
        JbpmContext jbpmContext = createJbpmContext();
        try {
            ProcessInstance processInstance
                    = getHelloProcessInstance(jbpmContext);
            assertProcessInstance(jbpmContext, processInstance);
            assertNode("sayHello", processInstance);
            
            TaskInstance taskInstance = nextTaskInstance(jbpmContext, "hello-01");
            assertTaskInstance(jbpmContext, taskInstance);
            assertEquals("sayHello", taskInstance.getName());
            
            taskInstance.start("hello-01");
            jbpmContext.save(taskInstance);
            assertTaskInstance(jbpmContext, taskInstance);
            assertProcessInstance(jbpmContext, processInstance);
            assertNode("sayHello", processInstance);
            
            System.out.println("Hello, World!");
            
            taskInstance.end();
            jbpmContext.save(taskInstance);
            assertTaskInstance(jbpmContext, taskInstance);
            assertProcessInstance(jbpmContext, processInstance);
            assertNode("end", processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    ProcessInstance getHelloProcessInstance(JbpmContext jbpmContext) {
        GraphSession graphSession = jbpmContext.getGraphSession();
        ProcessDefinition processDefinition
                = graphSession.findLatestProcessDefinition("JbpmTest");
        List<?> processInstances
                = graphSession.findProcessInstances(processDefinition.getId());
        return (ProcessInstance) processInstances.get(0);
    }

    TaskInstance nextTaskInstance(JbpmContext jbpmContext, String actorId) {
        TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
        List<?> taskInstances = taskMgmtSession.findTaskInstances(actorId);
        if (!taskInstances.isEmpty()) {
            return (TaskInstance) taskInstances.get(0);
        }
        taskInstances = taskMgmtSession.findPooledTaskInstances(actorId);
        if (!taskInstances.isEmpty()) {
            return (TaskInstance) taskInstances.get(0);
        }
        return null;
    }

    void assertProcessDefinition(
            JbpmContext jbpmContext, ProcessDefinition processDefinition) {
        long processDefinitionId = processDefinition.getId();
        assertEquals(processDefinition,
                load(ProcessDefinition.class, processDefinitionId));
    }

    void assertProcessInstance(
            JbpmContext jbpmContext, ProcessInstance processInstance) {
        long processInstanceId = processInstance.getId();
        assertEquals(processInstance, jbpmContext
                .getProcessInstance(processInstanceId));
        assertEquals(processInstance,
                load(ProcessInstance.class, processInstanceId));
    }

    void assertStartState(ProcessInstance processInstance) {
        Node expected = processInstance.getProcessDefinition().getStartState();
        Node actual = processInstance.getRootToken().getNode();
        assertEquals(expected, actual);
    }

    void assertNode(String name, ProcessInstance processInstance) {
        Node expected = processInstance.getProcessDefinition().getNode(name);
        Node actual = processInstance.getRootToken().getNode();
        assertEquals(expected, actual);
    }

    void assertTaskInstance(
            JbpmContext jbpmContext, TaskInstance taskInstance) {
        long taskInstanceId = taskInstance.getId();
        assertEquals(taskInstance, jbpmContext.getTaskInstance(taskInstanceId));
        assertEquals(taskInstance, load(TaskInstance.class, taskInstanceId));
    }

    public static void main(String[] args) {
        SingletonS2ContainerFactory.setConfigPath("j2ee.dicon");
        SingletonS2ContainerFactory.init();
        try {
            JbpmConfiguration conf = JbpmConfiguration.getInstance();
            conf.dropSchema();
            conf.createSchema();
        } finally {
            SingletonS2ContainerFactory.destroy();
        }
    }

}
