package ru.dipech.cute.model.input;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class InputTask {
    private final String name;
    private final InputArgs args;
}
