package ru.dipech.cute.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class Processor {
    private final List<Arg> args = new LinkedList<>();

    public void process(String[] args) {
        Arrays.stream(args).forEach(arg -> this.args.add(detect(arg)));
    }

    public List<Arg> getConcreteArgs(Class<? extends Arg> argType) {
        return args.stream().filter(argType::isInstance).collect(Collectors.toList());
    }

    private Arg detect(String raw) {
        raw = StringUtils.strip(raw);
        if (StringUtils.startsWith(raw, "-")) {
            return new FlagArg(raw);
        }
        if (raw.contains("=")) {
            return new ParamArg(raw);
        }
        return new TaskArg(raw);
    }
}
