package example.hello.service.impl;

import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.seasar.jbpm.S2JbpmConfiguration;

import example.hello.dto.MessageDto;
import example.hello.service.HelloService;

public class JbpmDbHelloServiceImpl implements HelloService {

    S2JbpmConfiguration jbpmConfiguration;

    static Log log = LogFactory.getLog(JbpmDbHelloServiceImpl.class);

    public void setJbpmConfiguration(S2JbpmConfiguration jbpmConfiguration) {
        this.jbpmConfiguration = jbpmConfiguration;
    }

    public void deployProcessDefinition() {
        ProcessDefinition processDefinition = ProcessDefinition
                .parseXmlResource("example/hello/process/processdefinition.xml");
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            jbpmContext.deployProcessDefinition(processDefinition);
        } finally {
            jbpmContext.close();
        }
    }

    public MessageDto sayHello(String name) {
        newProcessInstance();
        start();
        setName(name);
        return getMessage();
    }

    void newProcessInstance() {
        log.debug("<<< newProcessInstance >>>");
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            GraphSession graphSession = jbpmContext.getGraphSession();
            ProcessDefinition processDefinition
                    = graphSession.findLatestProcessDefinition("HelloProcess");
            ProcessInstance processInstance
                    = new ProcessInstance(processDefinition);
            jbpmContext.save(processInstance);
            assertStartState(processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    void start() {
        log.debug("<<< start >>>");
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            ProcessInstance processInstance
                    = getHelloProcessInstance(jbpmContext);
            assertStartState(processInstance);
            processInstance.signal();
            jbpmContext.save(processInstance);
            assertNode("setName", processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    void setName(String name) {
        log.debug("<<< setName >>>");
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            ProcessInstance processInstance
                    = getHelloProcessInstance(jbpmContext);
            assertNode("setName", processInstance);
            TaskInstance taskInstance = nextTaskInstance(processInstance);
            assertEquals("setName", taskInstance.getName());
            taskInstance.start("hello-01");
            jbpmContext.save(taskInstance);
            // タスクを開始しても状態は変わらない。
            assertNode("setName", processInstance);
            
            taskInstance.setVariable("name", name);
            if (log.isDebugEnabled()) {
                log.debug("variables = " + taskInstance.getVariables());
            }
            
            taskInstance.end();
            jbpmContext.save(taskInstance);
            // タスクを終了すると次の待機状態まで進む。
            assertNode("getMessage", processInstance);
        } finally {
            jbpmContext.close();
        }
    }

    MessageDto getMessage() {
        log.debug("<<< getMessage >>>");
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            ProcessInstance processInstance
                    = getHelloProcessInstance(jbpmContext);
            assertNode("getMessage", processInstance);
            TaskInstance taskInstance = nextTaskInstance(jbpmContext, "hello-01");
            assertEquals("getMessage", taskInstance.getName());
            taskInstance.start("hello-01");
            jbpmContext.save(taskInstance);
            // タスクを開始しても状態は変わらない。
            assertNode("getMessage", processInstance);
            
            if (log.isDebugEnabled()) {
                log.debug("variables = " + taskInstance.getVariables());
            }
            MessageDto message = (MessageDto) taskInstance.getVariable("message");
            
            taskInstance.end();
            jbpmContext.save(taskInstance);
            // タスクを終了すると次の待機状態まで進む。
            assertNode("end", processInstance);
            return message;
        } finally {
            jbpmContext.close();
        }
    }

    ProcessInstance getHelloProcessInstance(JbpmContext jbpmContext) {
        GraphSession graphSession = jbpmContext.getGraphSession();
        ProcessDefinition processDefinition
                = graphSession.findLatestProcessDefinition("HelloProcess");
        List<?> processInstances
                = graphSession.findProcessInstances(processDefinition.getId());
        return (ProcessInstance) processInstances.get(0);
    }

    TaskInstance nextTaskInstance(ProcessInstance processInstance) {
        log.debug("<<< nextTaskInstance >>>");
        Token token = processInstance.getRootToken();
        Collection<?> taskInstances = processInstance
                .getTaskMgmtInstance().getUnfinishedTasks(token);
        if (log.isDebugEnabled()) {
            log.debug("taskInstances = " + taskInstances);
        }
        TaskInstance taskInstance
                = (TaskInstance) taskInstances.iterator().next();
        if (log.isDebugEnabled()) {
            log.debug("taskInstance = " + taskInstance);
            log.debug("  actorId    = " + taskInstance.getActorId());
        }
        return taskInstance;
    }

    TaskInstance nextTaskInstance(JbpmContext jbpmContext, String actorId) {
        log.debug("<<< nextTaskInstance >>>");
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

    void assertStartState(ProcessInstance processInstance) {
        Node expected = processInstance.getProcessDefinition().getStartState();
        Node actual = processInstance.getRootToken().getNode();
        assertSame(expected, actual);
    }

    void assertNode(String name, ProcessInstance processInstance) {
        Node expected = processInstance.getProcessDefinition().getNode(name);
        Node actual = processInstance.getRootToken().getNode();
        assertSame(expected, actual);
    }

    protected void assertSame(Object expected, Object actual) {
        Assert.assertSame(expected, actual);
    }

    protected void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

}
