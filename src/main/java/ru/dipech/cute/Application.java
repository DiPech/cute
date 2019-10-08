package ru.dipech.cute;

import ru.dipech.cute.input.Processor;
import ru.dipech.cute.state.State;

public class Application {
    public static void main(String[] args) {
        Processor processor = new Processor();
        try {
            processor.process(args);
            State state = State.getInstance(processor);
            state.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
