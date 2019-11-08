package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.InputArgs;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.task.Flag;
import ru.dipech.cute.model.task.Param;
import ru.dipech.cute.model.task.Task;
import ru.dipech.cute.service.parser.TaskParser;

import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.TASK_DATA_PATH;

@SpringBootTest
class TaskParserTest {

    @Autowired
    private TaskParser parser;

    @Test
    void parseTaskMinimum() {
        // @todo simplify minimum task because now we have FileParserTest
        Task expected = Task.builder().name("minimum").title("Absolute minimum for creating task").build();
        assertEquals(expected, parser.parse(Paths.get(TASK_DATA_PATH + "/minimum.sh")));
    }

    @Test
    void parseTaskMaximum() {
        // @todo simplify maximum task because now we have FileParserTest
        Task expected = Task.builder()
            .name("maximum")
            .title("Absolute maximum for creating task")
            .description("Execute task \"minimum\" before and after execution three times.")
            .executeBefore(new LinkedList<>(getMaximumTaskExecDependency()))
            .executeAfter(new LinkedList<>(getMaximumTaskExecDependency()))
            .flags(getMaximumTaskFlags())
            .params(getMaximumTaskParams())
            .build();
        assertEquals(expected, parser.parse(Paths.get(TASK_DATA_PATH + "/maximum.sh")));
    }

    private List<InputTask> getMaximumTaskExecDependency() {
        List<InputTask> result = new ArrayList<>();
        result.add(new InputTask("minimum", new InputArgs()));
        result.add(new InputTask("minimum", new InputArgs()));
        result.add(new InputTask("minimum", new InputArgs()));
        return result;
    }

    private Map<String, Flag> getMaximumTaskFlags() {
        Map<String, Flag> result = new HashMap<>();
        result.put("print", (new Flag("print", "Print something", 'p')));
        result.put("d", (new Flag("d", "Draw something", null)));
        return result;
    }

    private Map<String, Param> getMaximumTaskParams() {
        Map<String, Param> result = new HashMap<>();
        Param p1 = getFirstParam();
        Param p2 = getSecondParam();
        result.put(p1.getName(), p1);
        result.put(p2.getName(), p2);
        return result;
    }

    private Param getFirstParam() {
        return Param.builder()
            .name("s")
            .title("Defines some scope")
            .required(true)
            .validationPattern("[a-z]+")
            .build();
    }

    private Param getSecondParam() {
        List<String> defaultValue = new ArrayList<>();
        defaultValue.add("a");
        defaultValue.add("b");
        defaultValue.add("c");
        return Param.builder()
            .name("multi")
            .title("Parameter can accepts multiple values")
            .defaultValue(defaultValue)
            .canHaveMultipleValues(true)
            .validationPattern("[a-z]")
            .build();
    }
}
