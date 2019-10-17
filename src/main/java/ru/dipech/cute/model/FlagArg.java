package ru.dipech.cute.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.ArgParseException;

@ToString(callSuper = true)
@NoArgsConstructor
public class FlagArg extends Arg {
    public FlagArg(String name) {
        super(name);
    }

    @Override
    public FlagArg parse(String raw) {
        if (!raw.contains("-")) {
            throw new ArgParseException("Flag should contain dash prefix");
        }
        if (raw.contains("=") || raw.contains(":")) {
            throw new ArgParseException("Flag contains bad symbols");
        }
        String name = StringUtils.stripStart(raw, "-");
        int dashCount = raw.length() - name.length();
        if (dashCount > 2) {
            throw new ArgParseException("Too many dashes");
        }
        if (dashCount == 1 && name.length() > 1) {
            throw new ArgParseException("Flag name with one dash should be single symbol");
        }
        if (dashCount == 2 && name.length() == 1) {
            throw new ArgParseException("Flag name with two dashes should be longer");
        }
        this.name = name;
        return this;
    }
}
