package example.hello.process.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class LogActionHandler implements ActionHandler {

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(LogActionHandler.class);

    public void execute(ExecutionContext executionContext) {
        if (log.isDebugEnabled()) {
            log.debug("<<< execute >>>");
            log.debug("event      = " + executionContext.getEvent());
            log.debug("  source   = " + executionContext.getEventSource());
            log.debug("transition = " + executionContext.getTransition());
            log.debug("  source   = " + executionContext.getTransitionSource());
        }
    }

}
