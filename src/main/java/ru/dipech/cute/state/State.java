package ru.dipech.cute.state;

import lombok.RequiredArgsConstructor;
import ru.dipech.cute.model.AppContext;

@RequiredArgsConstructor
public abstract class State {
    protected final AppContext appContext;

    public static State getInstance(AppContext appContext) {
        if (appContext.getRuntimeContext().hasFlag("v")) {
            return new PrintAppVersionState(appContext);
        }
        if (appContext.getTaskContext().getInputTasks().size() > 0) {
            return new ExecuteTasksState(appContext);
        }
        return new PrintTasksState(appContext);
    }

    public abstract void execute();
}
