package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputArgs;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.input.TaskInputArg;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InputTaskParser {
    public List<InputTask> parse(List<InputArg> args) {
        List<InputTask> result = new LinkedList<>();
        InputTask inputTask = null;
        for (int i = 0; i < args.size(); i++) {
            InputArg inputArg = args.get(i);
            if (inputTask == null && inputArg instanceof TaskInputArg) {
                inputTask = new InputTask(inputArg.getName(), new InputArgs());
            }
            // Skip first flags and args, that appears before first task
            if (inputTask == null) {
                continue;
            }
            inputTask.getArgs().add(inputArg);
            boolean isLast = i == args.size() - 1;
            boolean isNextTaskInputArg = !isLast && (args.get(i + 1) instanceof TaskInputArg);
            if (isLast || isNextTaskInputArg) {
                result.add(inputTask);
                inputTask = null;
            }
        }
        return result;
    }
}
