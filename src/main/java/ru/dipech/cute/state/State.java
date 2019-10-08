package ru.dipech.cute.state;

import lombok.RequiredArgsConstructor;
import ru.dipech.cute.input.FlagArg;
import ru.dipech.cute.input.Processor;

import java.util.List;

@RequiredArgsConstructor
public abstract class State {
    protected final Processor processor;

    public static State getInstance(Processor processor) {
        List<FlagArg> flags = processor.getConcreteArgs(FlagArg.class);
        if (flags.size() == 1 && flags.get(0).getKey().equals("v")) {
            return new PrintAppVersionState(processor);
        }
        return new PrintCommandsState(processor);
    }

    public abstract void execute();
}
