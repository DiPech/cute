package ru.dipech.cute.model.input;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class InputArg {
    protected String name;

    public abstract InputArg parse(String raw);
}
