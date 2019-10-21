package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.RuntimeContext;
import ru.dipech.cute.model.input.*;
import ru.dipech.cute.service.RuntimeContextParser;
import ru.dipech.cute.util.TestUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.getArgMap;

@SpringBootTest
class RuntimeContextParserTest {

    @Autowired
    private RuntimeContextParser parser;

    @Test
    void parseSimpleInput() {
        InputTask inputTask = InputTask.builder()
            .flags(getArgMap(new FlagInputArg("v")))
            .params(new HashMap<>())
            .build();
        RuntimeContext expected = new RuntimeContext(inputTask);
        List<InputArg> input = new LinkedList<>();
        input.add(new FlagInputArg("v"));
        input.add(new TaskInputArg("task1"));
        input.add(new ParamInputArg("param", "value"));
        input.add(new TaskInputArg("task:sub:subtask2"));
        input.add(new FlagInputArg("f"));
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseDifficultInput() {
        InputTask inputTask = InputTask.builder()
            .flags(getArgMap(new FlagInputArg("unused"), new FlagInputArg("u"), new FlagInputArg("v")))
            .params(getArgMap(new ParamInputArg("notusedparam", "notusedvalue")))
            .build();
        RuntimeContext expected = new RuntimeContext(inputTask);
        assertEquals(expected, parser.parse(TestUtil.getInputArgList()));
    }

    @Test
    void parseEmptyInput() {
        RuntimeContext expected = new RuntimeContext(new InputTask(null, new HashMap<>(), new HashMap<>()));
        List<InputArg> input = new LinkedList<>();
        assertEquals(expected, parser.parse(input));
    }
}
