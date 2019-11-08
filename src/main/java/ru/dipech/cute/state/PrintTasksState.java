package ru.dipech.cute.state;

import ru.dipech.cute.model.context.AppContext;

public class PrintTasksState extends State {
    public PrintTasksState(AppContext appContext) {
        super(appContext);
    }

    public void execute() {
        System.out.println("Print tasks");
    }
}
