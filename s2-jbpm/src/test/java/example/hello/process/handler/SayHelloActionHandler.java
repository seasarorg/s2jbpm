package example.hello.process.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import example.hello.dto.MessageDto;
import example.hello.service.HelloService;
import example.hello.service.impl.HelloServiceImpl;

public class SayHelloActionHandler implements ActionHandler {

    private static final long serialVersionUID = 1L;

    static Log log = LogFactory.getLog(SayHelloActionHandler.class);

    HelloService service = new HelloServiceImpl();

    public void execute(ExecutionContext executionContext) {
        log.debug("<<< execute >>>");
        ContextInstance contextInstance
                = executionContext.getContextInstance();
        String name = (String) contextInstance.getVariable("name");
        MessageDto message = service.sayHello(name);
        log.debug("message = " + message);
        contextInstance.setVariable("message", message);
        executionContext.leaveNode();
    }

}
