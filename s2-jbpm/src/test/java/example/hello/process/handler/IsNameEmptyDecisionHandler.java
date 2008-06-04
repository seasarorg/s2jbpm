package example.hello.process.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

public class IsNameEmptyDecisionHandler implements DecisionHandler {

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(IsNameEmptyDecisionHandler.class);

    public String decide(ExecutionContext executionContext) {
        log.debug("<<< decide >>>");
        ContextInstance contextInstance
                = executionContext.getContextInstance();
        String name = (String) contextInstance.getVariable("name");
        return (name == null || name.length() == 0) ? "yes" : "no";
    }

}
