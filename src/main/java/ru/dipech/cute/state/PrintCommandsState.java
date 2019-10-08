package ru.dipech.cute.state;

import ru.dipech.cute.input.Processor;

public class PrintCommandsState extends State {
    public PrintCommandsState(Processor processor) {
        super(processor);
    }

    public void execute() {
        System.out.println("... command list ...");
    }
}
