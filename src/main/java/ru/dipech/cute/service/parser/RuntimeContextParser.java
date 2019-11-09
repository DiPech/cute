package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.context.RuntimeContext;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputArgs;
import ru.dipech.cute.model.input.TaskInputArg;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuntimeContextParser {
    public RuntimeContext parse(List<InputArg> args) {
        InputArgs inputArgs = new InputArgs();
        for (InputArg inputArg : args) {
            if (inputArg instanceof TaskInputArg) {
                break;
            }
            inputArgs.add(inputArg);
        }
        return new RuntimeContext(inputArgs);
    }
}
