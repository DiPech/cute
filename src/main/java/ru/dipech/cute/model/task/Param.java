package ru.dipech.cute.model.task;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Param extends Arg {
    private boolean required;
    private boolean canHaveMultipleValues;
    private String validationPattern;
    private List<String> defaultValue;

    @Builder
    public Param(String name, String title, Character shortcut, boolean required, boolean canHaveMultipleValues, String validationPattern, List<String> defaultValue) {
        super(name, title, shortcut);
        this.required = required;
        this.canHaveMultipleValues = canHaveMultipleValues;
        this.validationPattern = validationPattern;
        this.defaultValue = defaultValue;
    }
}
