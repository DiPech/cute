package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.*;
import ru.dipech.cute.service.InputTaskParser;
import ru.dipech.cute.util.TestUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.getArgMap;

@SpringBootTest
class InputTaskParserTest {

    @Autowired
    private InputTaskParser parser;

    @Test
    void parseSimpleInput() {
        List<InputTask> expected = new LinkedList<>();
        InputTask task = InputTask.builder()
            .name("task:name")
            .flags(getArgMap(new FlagInputArg("-v"), new FlagInputArg("--flag")))
            .params(getArgMap(new ParamInputArg("a", "b")))
            .build();
        expected.add(task);
        List<InputArg> input = new LinkedList<>();
        input.add(new TaskInputArg("task:name"));
        input.add(new FlagInputArg("-v"));
        input.add(new FlagInputArg("--flag"));
        input.add(new ParamInputArg("a", "b"));
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseDifficultInput() {
        List<InputTask> expected = new LinkedList<>();
        InputTask task1 = InputTask.builder()
            .name("task1")
            .flags(new HashMap<>())
            .params(getArgMap(new ParamInputArg("param", "value")))
            .build();
        InputTask task2 = InputTask.builder()
            .name("task:sub:subtask2")
            .flags(getArgMap(new FlagInputArg("f")))
            .params(getArgMap(new ParamInputArg("p1", "v1"), new ParamInputArg("p2", "v2"), new ParamInputArg("param3", "value3")))
            .build();
        expected.add(task1);
        expected.add(task2);
        assertEquals(expected, parser.parse(TestUtil.getInputArgList()));
    }

    @Test
    void parseInputWithMultipleParametersWithTheSameNamesButDifferentValues() {
        List<InputTask> expected = new LinkedList<>();
        InputTask task = InputTask.builder()
            .name("task:name")
            .flags(getArgMap())
            .params(getArgMap(new ParamInputArg("p", "v1", "v2", "v3")))
            .build();
        expected.add(task);
        List<InputArg> input = new LinkedList<>();
        input.add(new TaskInputArg("task:name"));
        input.add(new ParamInputArg("p", "v1"));
        input.add(new ParamInputArg("p", "v2"));
        input.add(new ParamInputArg("p", "v3"));
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseEmptyInput() {
        List<InputTask> expected = new LinkedList<>();
        List<InputArg> input = new LinkedList<>();
        assertEquals(expected, parser.parse(input));
    }
}
