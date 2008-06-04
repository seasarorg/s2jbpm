package example.hello.service.impl;

import example.hello.dto.MessageDto;
import example.hello.service.HelloService;

public class HelloServiceImpl implements HelloService {

    public MessageDto sayHello(String name) {
        MessageDto hello = new MessageDto(name);
        if (name == null || name.length() == 0) {
            hello.setBody("Hello, World!");
        } else {
            hello.setBody("Hello, " + name + "!");
        }
        return hello;
    }

}
