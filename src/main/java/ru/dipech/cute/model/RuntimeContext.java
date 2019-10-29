package ru.dipech.cute.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.dipech.cute.model.input.InputArgs;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RuntimeContext {
    private final InputArgs args;
}
