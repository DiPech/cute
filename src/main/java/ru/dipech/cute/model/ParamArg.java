package ru.dipech.cute.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dipech.cute.exception.ArgParseException;

@ToString(callSuper = true)
@NoArgsConstructor
public class ParamArg extends Arg {
    private String value;

    public ParamArg(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public ParamArg parse(String raw) {
        if (!raw.contains("=")) {
            throw new ArgParseException("Param should have equal sign");
        }
        int equalSignIndex = raw.indexOf("=");
        if (equalSignIndex == 0) {
            throw new ArgParseException("Missed parameter name");
        }
        name = raw.substring(0, equalSignIndex);
        value = raw.substring(equalSignIndex);
        return this;
    }
}
