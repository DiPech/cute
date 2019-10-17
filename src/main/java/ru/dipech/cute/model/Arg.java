package ru.dipech.cute.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Arg {
    protected String name;

    public abstract Arg parse(String raw);
}
