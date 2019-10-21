package ru.dipech.cute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.RuntimeContext;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.input.TaskInputArg;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RuntimeContextParser {
    private final InputTaskArgAdder inputTaskArgAdder;

    public RuntimeContext parse(List<InputArg> args) {
        InputTask inputTask = new InputTask(null, new HashMap<>(), new HashMap<>());
        for (InputArg inputArg : args) {
            if (inputArg instanceof TaskInputArg) {
                break;
            }
            inputTaskArgAdder.add(inputTask, inputArg);
        }
        return new RuntimeContext(inputTask);
    }
}
