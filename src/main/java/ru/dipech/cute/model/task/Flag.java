package ru.dipech.cute.model.task;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Flag extends Arg {
    @Builder
    public Flag(String name, String title, Character shortcut) {
        super(name, title, shortcut);
    }
}
