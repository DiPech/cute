package ru.dipech.cute.exception.parse;

import ru.dipech.cute.exception.InternalException;

public class ParseException extends InternalException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
