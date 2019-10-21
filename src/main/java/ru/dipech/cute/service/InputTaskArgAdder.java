package ru.dipech.cute.service;

import org.springframework.stereotype.Service;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.input.ParamInputArg;

@Service
public class InputTaskArgAdder {
    public void add(InputTask task, InputArg arg) {
        if (arg instanceof FlagInputArg) {
            task.addFlag((FlagInputArg) arg);
        } else if (arg instanceof ParamInputArg) {
            task.addParam((ParamInputArg) arg);
        }
    }
}
