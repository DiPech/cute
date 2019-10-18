package ru.dipech.cute.input;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.dipech.cute.model.Arg;
import ru.dipech.cute.model.FlagArg;
import ru.dipech.cute.model.ParamArg;
import ru.dipech.cute.model.TaskArg;

import java.util.LinkedList;
import java.util.List;

@Component
public class Parser {
    public List<Arg> parse(String[] input) {
        List<Arg> result = new LinkedList<>();
        for (String raw : input) {
            // Param?
            if (raw.contains("=")) {
                result.add((new ParamArg()).parse(raw));
                continue;
            }
            // Task?
            if (!raw.contains("-")) {
                result.add((new TaskArg()).parse(raw));
                continue;
            }
            // Flag?
            boolean isCombinedShortFlags = StringUtils.countMatches(raw, "-") == 1 && raw.length() > 2;
            if (!isCombinedShortFlags) {
                result.add((new FlagArg()).parse(raw));
                continue;
            }
            // Combined short flags e.g. "-abc" should be split into "-a -b -c".
            String flags = StringUtils.stripStart(raw, "-");
            for (char ch : flags.toCharArray()) {
                result.add((new FlagArg()).parse("-" + ch));
            }
        }
        return result;
    }
}
