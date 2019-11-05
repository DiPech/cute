package ru.dipech.cute.model.input;

import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.dipech.cute.service.validator.TaskNameValidator;

@ToString(callSuper = true)
@NoArgsConstructor
public class TaskInputArg extends InputArg {
    private static final TaskNameValidator validator = new TaskNameValidator();

    public TaskInputArg(String name) {
        super(name);
    }

    @Override
    public TaskInputArg parse(String raw) {
        validator.validate(raw);
        name = raw;
        return this;
    }
}
