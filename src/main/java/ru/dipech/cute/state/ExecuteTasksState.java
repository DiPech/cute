package ru.dipech.cute.state;

import ru.dipech.cute.model.AppContext;

public class ExecuteTasksState extends State {
    public ExecuteTasksState(AppContext appContext) {
        super(appContext);
    }

    public void execute() {
        System.out.println("Execute tasks");
    }
}
