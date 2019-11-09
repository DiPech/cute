package ru.dipech.cute.model.task;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class Arg {
    protected final String name;
    protected String title;
    protected Character shortcut;
}
