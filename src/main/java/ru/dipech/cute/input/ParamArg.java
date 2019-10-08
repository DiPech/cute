package ru.dipech.cute.input;

import lombok.Getter;
import lombok.ToString;
import ru.dipech.cute.exception.InternalException;

@Getter
@ToString(callSuper = true)
public class ParamArg extends Arg {

    private String key;

    public ParamArg(String raw) {
        super(raw);
    }

    @Override
    protected String parse(String raw) {
        if (!raw.contains("=")) {
            throw new InternalException("Arg value (" + raw + ") doesn't have equal sign.");
        }
        String[] parts = raw.split("=");
        if (parts.length != 2) {
            throw new InternalException("Arg value has many equal signs (" + raw + ").");
        }
        key = parts[0];
        String value = parts[1];
        if (value.length() == 0) {
            throw new InternalException("Arg value not specified (" + raw + ").");
        }
        return value;
    }
}
