package ru.dipech.cute.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.exception.TaskParseException;
import ru.dipech.cute.model.Task;
import ru.dipech.cute.service.validator.TaskNameValidator;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskParser {
    private static final TaskNameValidator taskNameValidator = new TaskNameValidator();

    public Task parse(Path taskPath, List<String> components) {
        String taskName = getTaskName(taskPath, components);
        try {
            taskNameValidator.validate(taskName);
        } catch (ArgParseException e) {
            throw new TaskParseException(e);
        }
        return new Task(taskName);
    }

    private String getTaskName(Path taskPath, List<String> components) {
        String fileName = taskPath.getFileName().toString();
        String fileBaseName = FilenameUtils.removeExtension(fileName);
        List<String> nameComponents = new LinkedList<>(components);
        nameComponents.add(fileBaseName);
        return StringUtils.join(nameComponents, ":");
    }
}
