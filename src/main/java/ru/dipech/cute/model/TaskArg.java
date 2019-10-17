package ru.dipech.cute.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.ArgParseException;

@ToString(callSuper = true)
@NoArgsConstructor
public class TaskArg extends Arg {
    public TaskArg(String name) {
        super(name);
    }

    @Override
    public TaskArg parse(String raw) {
        if (raw.contains("-") || raw.contains("=")) {
            throw new ArgParseException("Task name contains bad symbols");
        }
        if (!raw.equals(raw.toLowerCase())) {
            throw new ArgParseException("Task name should be in lowercase");
        }
        // Task name should be like "xxx:yyy:zzz", not like ":xxx" or "yyy:" or "xxx::yyy" etc
        String[] parts = StringUtils.split(raw, ":");
        int colonsCount = StringUtils.countMatches(raw, ":");
        if (parts.length != colonsCount + 1) {
            throw new ArgParseException("Malformed task name");
        }
        name = raw;
        return this;
    }
}
