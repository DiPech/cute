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
    public int getFlagsCount() {
        return holder.getFlagsCount();
    }

    @Override
    public int getParamsCount() {
        return holder.getParamsCount();
    }

    @Override
    public int getParamValuesCount(String name) {
        return holder.getParamValuesCount(name);
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
    public String getParamValue(String name) {
        return holder.getParamValue(name);
    }

    @Override
    public String[] getParamValues(String name) {
        return holder.getParamValues(name);
    }
}
