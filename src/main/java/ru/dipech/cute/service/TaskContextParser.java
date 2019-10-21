package ru.dipech.cute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.TaskContext;
import ru.dipech.cute.model.input.InputTask;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskContextParser {
    public TaskContext parse(List<InputTask> inputTasks) {
        return new TaskContext(inputTasks);
    }
}
