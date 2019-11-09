package ru.dipech.cute.model.input;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class InputTask {
    private final String name;
    private final InputArgs args;
}
