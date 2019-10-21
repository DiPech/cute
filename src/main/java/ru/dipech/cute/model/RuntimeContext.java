package ru.dipech.cute.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.service.ArgAccessor;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RuntimeContext implements ArgAccessor {
    // Maybe a little bit hacky and dirty, but acceptable for this moment
    private final InputTask holder;

    @Override
    public int flagsCount() {
        return holder.flagsCount();
    }

    @Override
    public int paramsCount() {
        return holder.paramsCount();
    }

    @Override
    public boolean hasFlag(String name) {
        return holder.hasFlag(name);
    }

    @Override
    public boolean hasParam(String name) {
        return holder.hasParam(name);
    }

    @Override
    public String getParam(String name) {
        return holder.getParam(name);
    }
}
