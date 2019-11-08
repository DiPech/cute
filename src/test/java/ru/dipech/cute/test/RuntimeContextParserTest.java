package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.context.RuntimeContext;
import ru.dipech.cute.model.input.*;
import ru.dipech.cute.service.parser.RuntimeContextParser;
import ru.dipech.cute.util.TestUtil;

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
        RuntimeContext expected = new RuntimeContext(new InputArgs(getArgMap(new FlagInputArg("v")), getArgMap()));
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
        RuntimeContext expected = new RuntimeContext(new InputArgs(
            getArgMap(new FlagInputArg("unused"), new FlagInputArg("u"), new FlagInputArg("v")),
            getArgMap(new ParamInputArg("notusedparam", "notusedvalue"))
        ));
        assertEquals(expected, parser.parse(TestUtil.getInputArgList()));
    }

    @Test
    void parseEmptyInput() {
        RuntimeContext expected = new RuntimeContext(new InputArgs());
        List<InputArg> input = new LinkedList<>();
        assertEquals(expected, parser.parse(input));
    }
}
