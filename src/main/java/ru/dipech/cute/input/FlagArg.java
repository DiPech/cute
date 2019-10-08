package ru.dipech.cute.input;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.InternalException;

@Getter
@ToString(callSuper = true)
public class FlagArg extends Arg {

    private String key;

    public FlagArg(String raw) {
        super(raw);
    }

    @Override
    protected String parse(String raw) {
        if (!raw.contains("-")) {
            throw new InternalException("Arg value (" + raw + ") doesn't have dash sign.");
        }
        String cleaned = StringUtils.stripStart(raw, "-");
        int dashCount = raw.length() - cleaned.length();
        if (dashCount > 2) {
            throw new InternalException("Incorrect arg value - too many dashes (" + raw + ").");
        }
        String key, value = "";
        if (cleaned.contains("=")) {
            ParamArg arg = new ParamArg(cleaned);
            key = arg.getKey();
            value = arg.getValue();
        } else {
            key = cleaned;
        }
        if (dashCount == 1 && key.length() > 1) {
            throw new InternalException("Incorrect arg value - one dash but long key (" + raw + ").");
        }
        this.key = key;
        return value;
    }
}
