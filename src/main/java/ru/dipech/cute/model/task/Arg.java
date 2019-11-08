package ru.dipech.cute.model.task;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Arg {
    protected String name;
    protected String title;
    protected Character shortcut;
}
