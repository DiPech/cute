package ru.dipech.cute.model.input;

import lombok.*;
import ru.dipech.cute.service.ArgAccessor;

import java.util.Map;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InputTask implements ArgAccessor {
    private String name;
    private Map<String, FlagInputArg> flags;
    private Map<String, ParamInputArg> params;

    public void addFlag(FlagInputArg arg) {
        flags.put(arg.getName(), arg);
    }

    public void addParam(ParamInputArg arg) {
        params.put(arg.getName(), arg);
    }

    @Override
    public int flagsCount() {
        return flags.size();
    }

    @Override
    public int paramsCount() {
        return params.size();
    }

    @Override
    public boolean hasFlag(String name) {
        return flags.containsKey(name);
    }

    @Override
    public boolean hasParam(String name) {
        return params.containsKey(name);
    }

    @Override
    public String getParam(String name) {
        return params.getOrDefault(name, new ParamInputArg()).getValue();
    }
}
