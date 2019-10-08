package ru.dipech.cute.log;

import ru.dipech.cute.exception.InternalException;
import ru.dipech.cute.input.Processor;

import java.util.Objects;

public class Logger {
    private static Logger INSTANCE;
    private final Processor processor;
    private Boolean printLogs;

    private Logger(Processor processor) {
        Objects.requireNonNull(processor);
        this.processor = processor;
    }

    public static Logger makeInstance(Processor processor) {
        if (INSTANCE != null) {
            throw new InternalException("Logger already made.");
        }
        INSTANCE = new Logger(processor);
        return INSTANCE;
    }

    public static Logger getInstance() {
        Objects.requireNonNull(INSTANCE);
        return INSTANCE;
    }

    public void log(String msg) {
        if (canLog()) {
            System.out.println(msg);
        }
    }

    private Boolean canLog() {
        if (printLogs == null) {
            printLogs = processor.hasFlag("l");
        }
        return printLogs;
    }
}
