package ru.dipech.cute.input;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
abstract public class Arg {
    protected final String value;

    public Arg(String raw) {
        value = parse(raw);
    }

    protected abstract String parse(String raw);
}
