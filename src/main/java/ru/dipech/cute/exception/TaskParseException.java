package ru.dipech.cute.exception;

public class TaskParseException extends InternalException {
    public TaskParseException(Throwable e) {
        super(e);
    }

    public TaskParseException(String message) {
        super(message);
    }
}
