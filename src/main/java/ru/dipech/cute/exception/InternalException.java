package ru.dipech.cute.exception;

public class InternalException extends RuntimeException {
    public InternalException(String msg) {
        super(msg);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }
}
