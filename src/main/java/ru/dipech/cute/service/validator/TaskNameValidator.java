package ru.dipech.cute.service.validator;

import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.parse.ArgParseException;

import java.util.regex.Pattern;

public class TaskNameValidator extends Validator<String, ArgParseException> {
    @Override
    protected void doValidate(String name) {
        if (!name.equals(name.toLowerCase())) {
            addError("Task name should be in lowercase.");
        }
        String[] parts = StringUtils.split(name, ":");
        for (String part : parts) {
            if (part.length() == 0 || StringUtils.startsWith(part, "-") || !isNamePartValid(part)) {
                addError("Task name part is wrong: " + part + ".");
            }
        }
        // Task name should be like "xxx:yyy:zzz", not like ":xxx" or "yyy:" or "xxx::yyy" etc
        int colonsCount = StringUtils.countMatches(name, ":");
        if (parts.length != colonsCount + 1) {
            addError("Malformed task name.");
        }
    }

    private boolean isNamePartValid(String namePart) {
        return Pattern.compile("[a-z0-9\\-]+").matcher(namePart).matches();
    }

    @Override
    protected ArgParseException getException(String message) {
        return new ArgParseException(message);
    }
}
