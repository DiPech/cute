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
    public int getFlagsCount() {
        return flags.size();
    }

    @Override
    public int getParamsCount() {
        return params.size();
    }

    @Override
    public int getParamValuesCount(String name) {
        if (!hasParam(name)) {
            return 0;
        }
        return getParamValues(name).length;
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
    public String getParamValue(String name) {
        if (!hasParam(name)) {
            return null;
        }
        String[] values = getParamValues(name);
        return values.length > 0 ? values[0] : null;
    }

    @Override
    public String[] getParamValues(String name) {
        if (!hasParam(name)) {
            return new String[]{};
        }
        return params.get(name).getValues().toArray(new String[]{});
    }

    public ParamInputArg getParam(String name) {
        return params.get(name);
    }
}
