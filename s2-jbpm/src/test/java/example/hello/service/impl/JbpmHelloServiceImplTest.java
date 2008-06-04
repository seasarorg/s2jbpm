package example.hello.service.impl;

import junit.framework.TestCase;
import example.hello.dto.MessageDto;

public class JbpmHelloServiceImplTest extends TestCase {

    JbpmHelloServiceImpl service;

    protected void setUp() throws Exception {
        service = new JbpmHelloServiceImpl();
    }

    protected void tearDown() throws Exception {
        service = null;
    }

    public void testSayHello() {
        assertEquals("Hello, World!", service.sayHello(null));
    }

    public void testSayHelloEmpty() {
        assertEquals("Hello, World!", service.sayHello(""));
    }

    public void testSayHelloName() {
        assertEquals("Hello, Hoge!", service.sayHello("Hoge"));
    }

    void assertEquals(String body, MessageDto message) {
        System.out.println(message.getBody());
        assertEquals(body, message.getBody());
    }

}
