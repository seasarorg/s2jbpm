package example.exception;

public class ExampleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExampleException() {
    }

    public ExampleException(String message) {
        super(message);
    }

    public ExampleException(Throwable cause) {
        super(cause);
    }

    public ExampleException(String message, Throwable cause) {
        super(message, cause);
    }

}
