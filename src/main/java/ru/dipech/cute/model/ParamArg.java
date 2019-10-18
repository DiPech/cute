package ru.dipech.cute.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
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
        if (!raw.contains("=") || !raw.contains("-")) {
            throw new ArgParseException("Param should have equal sign and dash prefix");
        }
        raw = StringUtils.stripStart(raw, "-");
        int equalSignIndex = raw.indexOf("=");
        if (equalSignIndex == 0) {
            throw new ArgParseException("Missed parameter name");
        }
        name = raw.substring(0, equalSignIndex);
        value = raw.substring(equalSignIndex);
        return this;
    }
}
