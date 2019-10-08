package ru.dipech.cute.state;

import lombok.RequiredArgsConstructor;
import ru.dipech.cute.input.Processor;
import ru.dipech.cute.input.CommandArg;

import java.util.List;

@RequiredArgsConstructor
public abstract class State {
    protected final Processor processor;

    public static State getInstance(Processor processor) {
        if (processor.hasFlag("v")) {
            return new PrintAppVersionState(processor);
        }
        List<CommandArg> commandArgs = processor.getConcreteArgs(CommandArg.class);
        if (commandArgs.size() > 0) {
            return new ExecuteCommandsState(processor);
        }
        return new PrintCommandsState(processor);
    }

    public abstract void execute();
}
