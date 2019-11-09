package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.context.TaskContext;
import ru.dipech.cute.model.input.InputTask;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskContextParser {
    public TaskContext parse(List<InputTask> inputTasks) {
        return new TaskContext(inputTasks);
    }
}
