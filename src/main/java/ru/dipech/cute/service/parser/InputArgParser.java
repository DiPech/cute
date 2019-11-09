package ru.dipech.cute.service.parser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.ParamInputArg;
import ru.dipech.cute.model.input.TaskInputArg;

import java.util.LinkedList;
import java.util.List;

@Service
public class InputArgParser {
    public List<InputArg> parse(List<String> input) {
        List<InputArg> result = new LinkedList<>();
        for (String raw : input) {
            // Param?
            if (raw.contains("=")) {
                result.add((new ParamInputArg()).parse(raw));
                continue;
            }
            // Task?
            if (!raw.contains("-")) {
                result.add((new TaskInputArg()).parse(raw));
                continue;
            }
            // Flag?
            boolean isCombinedShortFlags = StringUtils.countMatches(raw, "-") == 1 && raw.length() > 2;
            if (!isCombinedShortFlags) {
                result.add((new FlagInputArg()).parse(raw));
                continue;
            }
            // Combined short flags e.g. "-abc" should be split into "-a -b -c".
            String flags = StringUtils.stripStart(raw, "-");
            for (char ch : flags.toCharArray()) {
                result.add((new FlagInputArg()).parse("-" + ch));
            }
        }
        return result;
    }
}
