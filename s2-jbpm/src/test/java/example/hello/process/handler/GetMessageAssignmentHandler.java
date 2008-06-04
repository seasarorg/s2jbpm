package example.hello.process.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

public class GetMessageAssignmentHandler implements AssignmentHandler {

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(GetMessageAssignmentHandler.class);

    public void assign(
            Assignable assignable,
            ExecutionContext executionContext) {
        log.debug("<<< assign >>>");
        assignable.setActorId("hello-01");
    }

}
