package ru.dipech.cute.input;

import lombok.ToString;

@ToString(callSuper = true)
public class CommandArg extends Arg {

    public CommandArg(String raw) {
        super(raw);
    }

    @Override
    protected String parse(String raw) {
        return raw;
    }
}
