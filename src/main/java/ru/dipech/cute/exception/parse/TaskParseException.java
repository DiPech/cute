package ru.dipech.cute.exception.parse;

public class TaskParseException extends ParseException {
    public TaskParseException(Throwable e) {
        super(e);
    }

    public TaskParseException(String message) {
        super(message);
    }
}
