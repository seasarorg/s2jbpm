package example.hello.service.impl;

import org.seasar.extension.unit.S2TestCase;

import example.hello.dto.MessageDto;

public class JbpmDbHelloServiceImplTest extends S2TestCase {

    JbpmDbHelloServiceImpl service;

    protected void setUp() throws Exception {
        include("JbpmDbHelloServiceImplTest.dicon");
    }

    public void testSayHelloTx() {
        service.deployProcessDefinition();
        assertEquals("Hello, World!", service.sayHello(null));
    }

    public void testSayHelloEmptyTx() {
        service.deployProcessDefinition();
        assertEquals("Hello, World!", service.sayHello(""));
    }

    public void testSayHelloNameTx() {
        service.deployProcessDefinition();
        assertEquals("Hello, Hoge!", service.sayHello("Hoge"));
    }

    void assertEquals(String body, MessageDto message) {
        System.out.println(message.getBody());
        assertEquals(body, message.getBody());
    }

}
