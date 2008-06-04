package example.hello.service.impl;

import java.util.Collection;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

import example.hello.dto.MessageDto;
import example.hello.service.HelloService;

public class JbpmHelloServiceImpl implements HelloService {

    static ProcessDefinition helloProcess;

    static {
        helloProcess = ProcessDefinition.parseXmlResource(
                "example/hello/process/processdefinition.xml");
    }

    static Log log = LogFactory.getLog(JbpmHelloServiceImpl.class);

    public MessageDto sayHello(String name) {
        ProcessInstance processInstance = newProcessInstance();
        start(processInstance);
        setName(processInstance, name);
        MessageDto message = getMessage(processInstance);
        return message;
    }

    ProcessInstance newProcessInstance() {
        log.debug("<<< newProcessInstance >>>");
        ProcessInstance processInstance = new ProcessInstance(helloProcess);
        assertStartState(processInstance);
        return processInstance;
    }

    void start(ProcessInstance processInstance) {
        log.debug("<<< start >>>");
        processInstance.signal();
        assertNode("setName", processInstance);
    }

    void setName(ProcessInstance processInstance, String name) {
        log.debug("<<< setName >>>");
        TaskInstance taskInstance = nextTaskInstance(processInstance);
        taskInstance.start("hello-01");
        // タスクを開始しても状態は変わらない。
        assertNode("setName", processInstance);
        
        taskInstance.setVariable("name", name);
        if (log.isDebugEnabled()) {
            log.debug("variables = " + taskInstance.getVariables());
        }
        
        taskInstance.end();
        // タスクを終了すると次の待機状態まで進む。
        assertNode("getMessage", processInstance);
    }

    MessageDto getMessage(ProcessInstance processInstance) {
        log.debug("<<< getMessage >>>");
        TaskInstance taskInstance = nextTaskInstance(processInstance);
        taskInstance.start("hello-01");
        // タスクを開始しても状態は変わらない。
        assertNode("getMessage", processInstance);
        
        if (log.isDebugEnabled()) {
            log.debug("variables = " + taskInstance.getVariables());
        }
        MessageDto message = (MessageDto) taskInstance.getVariable("message");
        
        taskInstance.end();
        // タスクを終了すると次の待機状態まで進む。
        assertNode("end", processInstance);
        return message;
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

}
