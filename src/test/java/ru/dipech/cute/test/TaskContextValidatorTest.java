package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.TaskContextValidateException;
import ru.dipech.cute.model.TaskContext;
import ru.dipech.cute.model.input.InputArgs;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.service.ScanPathParser;
import ru.dipech.cute.service.validator.TaskContextValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.dipech.cute.util.TestUtil.PARSER_DATA_PATH;

@SpringBootTest
class TaskContextValidatorTest {
    @Autowired
    private ScanPathParser scanPathParser;
    @Autowired
    private TaskContextValidator taskContextValidator;

    @Test
    void validateValidTaskContext() {
        TaskContext taskContext = getValidTaskContext();
        String message = getValidationMessage(taskContext);
        assertNull(message);
    }

    @Test
    void validateTaskContextWithCollisions() {
        TaskContext taskContext = getTaskContextWithCollisions();
        String message = getValidationMessage(taskContext);
        assertTrue(message.contains("Task «bbb» has in multiple scan paths"));
        assertTrue(message.contains("Task «ccc:aaa» has in multiple scan paths"));
    }

    @Test
    void validateTaskContextWithNotFoundTasks() {
        TaskContext taskContext = getTaskContextWithNotFoundTasks();
        String message = getValidationMessage(taskContext);
        assertTrue(message.contains("Task «task:with:bad:name» not found in scan paths"));
        assertTrue(message.contains("Task «another-bad-named-task» not found in scan paths"));
    }

    @Test
    void validateTaskContextWithCollisionsAndWithNotFoundTasks() {
        TaskContext taskContext = getTaskContextWithCollisionsAndWithNotFoundTasks();
        String message = getValidationMessage(taskContext);
        assertTrue(message.contains("Task «bbb» has in multiple scan paths"));
        assertTrue(message.contains("Task «ccc:aaa» has in multiple scan paths"));
        assertTrue(message.contains("Task «my-dick:is-big» not found in scan paths"));
    }

    /**
     * No task name collisions and no not found tasks
     */
    private TaskContext getValidTaskContext() {
        List<InputTask> inputTasks = new ArrayList<>();
        inputTasks.add(new InputTask("aaa", new InputArgs()));
        inputTasks.add(new InputTask("ccc:bbb", new InputArgs()));
        inputTasks.add(new InputTask("bbb:fff:mmm", new InputArgs()));
        TaskContext taskContext = new TaskContext(inputTasks);
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/multi-level"));
        return taskContext;
    }

    /**
     * Returns tasks from two contexts and there are tasks with the same names - "bbb" and "ccc:aaa"
     */
    private TaskContext getTaskContextWithCollisions() {
        List<InputTask> inputTasks = new ArrayList<>();
        inputTasks.add(new InputTask("bbb:fff:rrr", new InputArgs()));
        TaskContext taskContext = new TaskContext(inputTasks);
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/multi-level"));
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/collision"));
        return taskContext;
    }

    /**
     * Returns task context where passed wrong task names (not found tasks in scan paths)
     */
    private TaskContext getTaskContextWithNotFoundTasks() {
        List<InputTask> inputTasks = new ArrayList<>();
        inputTasks.add(new InputTask("aaa", new InputArgs()));
        inputTasks.add(new InputTask("task:with:bad:name", new InputArgs()));
        inputTasks.add(new InputTask("eee", new InputArgs()));
        inputTasks.add(new InputTask("another-bad-named-task", new InputArgs()));
        TaskContext taskContext = new TaskContext(inputTasks);
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/multi-level"));
        return taskContext;
    }

    /**
     * Returns combination of two methods above
     */
    private TaskContext getTaskContextWithCollisionsAndWithNotFoundTasks() {
        List<InputTask> inputTasks = new ArrayList<>();
        inputTasks.add(new InputTask("ccc:bbb", new InputArgs()));
        inputTasks.add(new InputTask("my-dick:is-big", new InputArgs()));
        TaskContext taskContext = new TaskContext(inputTasks);
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/multi-level"));
        taskContext.addScanPath(scanPathParser.parse(PARSER_DATA_PATH + "/collision"));
        return taskContext;
    }

    private String getValidationMessage(TaskContext taskContext) {
        String message = null;
        try {
            taskContextValidator.validate(taskContext);
        } catch (TaskContextValidateException e) {
            message = e.getMessage();
        }
        return message;
    }
}
