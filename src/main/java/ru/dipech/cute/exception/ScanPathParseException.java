package ru.dipech.cute.exception;

public class ScanPathParseException extends InternalException {
    public ScanPathParseException(String message) {
        super(message);
    }

    public ScanPathParseException(Throwable cause) {
        super(cause);
    }
}
