package ru.dipech.cute.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class Processor {
    private final List<Arg> args = new LinkedList<>();

    public void process(String[] args) {
        Arrays.stream(args).forEach(arg -> this.args.add(detect(arg)));
    }

    @SuppressWarnings("unchecked")
    public <T extends Arg> List<T> getConcreteArgs(Class<T> argClass) {
        return (List<T>) args.stream().filter(argClass::isInstance).collect(Collectors.toList());
    }

    public boolean hasFlag(String key) {
        Optional<FlagArg> flag = getConcreteArgs(FlagArg.class).stream().filter(a->a.getKey().equals(key)).findFirst();
        return flag.isPresent();
    }

    private Arg detect(String raw) {
        raw = StringUtils.strip(raw);
        if (StringUtils.startsWith(raw, "-")) {
            return new FlagArg(raw);
        }
        if (raw.contains("=")) {
            return new ParamArg(raw);
        }
        return new CommandArg(raw);
    }
}
