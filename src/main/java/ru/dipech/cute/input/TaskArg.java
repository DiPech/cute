package ru.dipech.cute.input;

import lombok.ToString;

@ToString(callSuper = true)
public class TaskArg extends Arg {

    public TaskArg(String raw) {
        super(raw);
    }

    @Override
    protected String parse(String raw) {
        return raw;
    }
}
