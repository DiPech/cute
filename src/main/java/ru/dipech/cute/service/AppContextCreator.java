package ru.dipech.cute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.context.AppContext;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.service.parser.InputArgParser;
import ru.dipech.cute.service.parser.InputTaskParser;
import ru.dipech.cute.service.parser.RuntimeContextParser;
import ru.dipech.cute.service.parser.TaskContextParser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppContextCreator {
    private final InputArgParser inputArgParser;
    private final InputTaskParser inputTaskParser;
    private final RuntimeContextParser runtimeContextParser;
    private final TaskContextParser taskContextParser;

    public AppContext create(List<String> rawArgs) {
        List<InputArg> args = inputArgParser.parse(rawArgs);
        List<InputTask> inputTasks = inputTaskParser.parse(args);
        return new AppContext(runtimeContextParser.parse(args), taskContextParser.parse(inputTasks));
    }
}
