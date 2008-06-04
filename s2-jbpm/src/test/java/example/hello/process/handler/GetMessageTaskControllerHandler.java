package example.hello.process.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.def.TaskControllerHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class GetMessageTaskControllerHandler implements TaskControllerHandler {

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(GetMessageTaskControllerHandler.class);

    public void initializeTaskVariables(
            TaskInstance taskInstance,
            ContextInstance contextInstance, Token token) {
        log.debug("<<< initializeTaskVariables >>>");
        taskInstance.setVariable("message",
                contextInstance.getVariable("message"));
    }

    public void submitTaskVariables(
            TaskInstance taskInstance,
            ContextInstance contextInstance, Token token) {
        log.debug("<<< submitTaskVariables >>>");
    }

}
