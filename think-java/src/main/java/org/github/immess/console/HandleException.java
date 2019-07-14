package org.github.immess.console;

public class HandleException extends RuntimeException {
    public HandleException(String s) {
        super(s);
    }

    public HandleException(String message, Throwable cause) {
        super(message, cause);
    }
}
