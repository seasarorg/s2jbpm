package example.hello.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;

    String body;

    public MessageDto() {
    }

    public MessageDto(String name) {
        this(name, null);
    }

    public MessageDto(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public MessageDto(MessageDto other) {
        this.name = other.name;
        this.body = other.body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return getClass().getSimpleName()
                + " {name=" + name
                + ", body=" + body + "}";
    }

}
