package ru.dipech.cute.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.TaskContextValidateException;
import ru.dipech.cute.model.ScanPath;
import ru.dipech.cute.model.TaskContext;
import ru.dipech.cute.service.TaskContextMappingBuilder;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskContextValidator extends Validator<TaskContext, TaskContextValidateException> {
    private final TaskContextMappingBuilder taskContextMappingBuilder;

    @Override
    protected void doValidate(TaskContext taskContext) {
        validateCollisions(taskContext);
        validateInputTasks(taskContext);
    }

    private void validateCollisions(TaskContext taskContext) {
        taskContextMappingBuilder.build(taskContext);
        Set<String> collidedTaskNames = taskContextMappingBuilder.getCollidedTaskNames();
        collidedTaskNames.forEach(taskName -> addError("Task «" + taskName + "» has in multiple scan paths."));
    }

    private void validateInputTasks(TaskContext taskContext) {
        Map<String, ScanPath> mapping = taskContextMappingBuilder.build(taskContext);
        taskContext.getInputTasks().forEach(inputTask -> {
            String taskName = inputTask.getName();
            if (!mapping.containsKey(taskName)) {
                addError("Task «" + taskName + "» not found in scan paths");
            }
        });
    }

    @Override
    protected TaskContextValidateException getException(String message) {
        return new TaskContextValidateException(message);
    }
}
