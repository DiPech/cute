package ru.dipech.cute.command;

import ru.dipech.cute.input.ParamArg;

import java.util.List;

public class Executor {
    public void execute(Command command, List<ParamArg> paramArgs) {
        System.out.println("To be implemented --- " + command + " --- " + paramArgs);
    }
}
