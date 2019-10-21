package ru.dipech.cute.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppContext {
    private final RuntimeContext runtimeContext;
    private final TaskContext taskContext;
}
