package ru.dipech.cute.model.input;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InputArgs {
    private Map<String, FlagInputArg> flags = new HashMap<>();
    private Map<String, ParamInputArg> params = new HashMap<>();

    public void add(InputArg inputArg) {
        if (inputArg instanceof FlagInputArg) {
            flags.put(inputArg.getName(), (FlagInputArg) inputArg);
        } else if (inputArg instanceof ParamInputArg) {
            ParamInputArg paramArg = (ParamInputArg) inputArg;
            if (hasParam(paramArg.getName())) {
                ParamInputArg alreadyParamArg = params.get(paramArg.getName());
                paramArg.getValues().forEach(alreadyParamArg::addValue);
            } else {
                params.put(paramArg.getName(), paramArg);
            }
        }
    }

    public int getFlagsCount() {
        return flags.size();
    }

    public int getParamsCount() {
        return params.size();
    }

    public int getParamValuesCount(String name) {
        if (!hasParam(name)) {
            return 0;
        }
        return getParamValues(name).size();
    }

    public boolean hasFlag(String name) {
        return flags.containsKey(name);
    }

    public boolean hasParam(String name) {
        return params.containsKey(name);
    }

    public String getParamValue(String name) {
        if (!hasParam(name)) {
            return null;
        }
        List<String> values = getParamValues(name);
        return values.size() > 0 ? values.get(0) : null;
    }

    public List<String> getParamValues(String name) {
        if (!hasParam(name)) {
            return new ArrayList<>();
        }
        return params.get(name).getValues();
    }
}
