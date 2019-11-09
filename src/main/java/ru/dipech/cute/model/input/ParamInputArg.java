package ru.dipech.cute.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.parse.ArgParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ParamInputArg extends InputArg {
    private List<String> values;

    public ParamInputArg(String name, List<String> values) {
        super(name);
        this.values = values;
    }

    public ParamInputArg(String name, String... values) {
        super(name);
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    @Override
    public ParamInputArg parse(String raw) {
        if (!raw.contains("=") || !raw.contains("-")) {
            throw new ArgParseException("Param should have equal sign and dash prefix");
        }
        raw = StringUtils.stripStart(raw, "-");
        int equalSignIndex = raw.indexOf("=");
        if (equalSignIndex == 0) {
            throw new ArgParseException("Missed parameter name");
        }
        name = raw.substring(0, equalSignIndex);
        String value = raw.substring(equalSignIndex + 1);
        values = new ArrayList<>();
        values.add(value);
        return this;
    }

    public void addValue(String value) {
        values.add(value);
    }
}
