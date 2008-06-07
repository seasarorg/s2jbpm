package org.seasar.jbpm.graph.handler;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.seasar.extension.unit.S2TestCase;

import example.hello.dto.MessageDto;
import example.hello.service.HelloService;
import example.hello.service.impl.HelloServiceImpl;

public class GenericActionHandlerTest extends S2TestCase {

    GenericActionHandler handler;

    HelloService helloService;

    protected void setUp() throws Exception {
        helloService = new HelloServiceImpl();
        register(helloService, "helloService");
    }

    public void testExecute() {
        ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(
                "<process-definition name='GenericActionHandlerTest'>" +
                "  <start-state name='start'>" +
                "    <transition to='sayHello'/>" +
                "  </start-state>" +
                "  <node name='sayHello'>" +
                "    <action class='org.seasar.jbpm.graph.handler.GenericActionHandler'>" +
                "      <componentName>helloService</componentName>" +
                "      <methodName>sayHello</methodName>" +
                "      <parameterNames>" +
                "        <element>name</element>" +
                "      </parameterNames>" +
                "      <resultName>message</resultName>" +
                "    </action>" +
                "    <transition to='end'/>" +
                "  </node>" +
                "  <end-state name='end'/>" +
                "</process-definition>");
        
        ProcessInstance processInstance
                = new ProcessInstance(processDefinition);
        ContextInstance contextInstance = processInstance.getContextInstance();
        contextInstance.setVariable("name", "Hoge");
        processInstance.signal();
        assertEquals("Hello, Hoge!", getMessageBody(contextInstance));
    }

    String getMessageBody(ContextInstance contextInstance) {
        Object message = contextInstance.getVariable("message");
        return ((MessageDto) message).getBody();
    }

    public void testGetComponent_Name() {
        handler = new GenericActionHandler();
        handler.componentName = "helloService";
        assertSame(helloService, handler.getComponent());
    }

    public void testGetComponent_Class() {
        handler = new GenericActionHandler();
        handler.componentClass = "example.hello.service.HelloService";
        assertSame(helloService, handler.getComponent());
    }

}
