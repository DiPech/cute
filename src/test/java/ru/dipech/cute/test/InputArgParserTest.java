package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.ParamInputArg;
import ru.dipech.cute.model.input.TaskInputArg;
import ru.dipech.cute.service.parser.InputArgParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InputArgParserTest {

    @Autowired
    private InputArgParser parser;

    @Test
    void parseSimpleInput() {
        List<InputArg> expected = new LinkedList<>();
        expected.add(new FlagInputArg("v"));
        expected.add(new ParamInputArg("p", "v"));
        expected.add(new TaskInputArg("task:name"));
        List<String> input = Arrays.asList("-v", "-p=v", "task:name");
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseDifficultInput() {
        List<InputArg> expected = Arrays.asList(
            new FlagInputArg("task"),
            new FlagInputArg("a"),
            new FlagInputArg("b"),
            new FlagInputArg("c"),
            new ParamInputArg("param", "value"),
            new FlagInputArg("d"),
            new FlagInputArg("e"),
            new TaskInputArg("another:name"),
            new FlagInputArg("flag")
        );
        List<String> input = Arrays.asList("task", "-abc", "--param=value", "-de", "another:name", "--flag");
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseEmptyInput() {
        List<InputArg> expected = new LinkedList<>();
        assertEquals(expected, parser.parse(new ArrayList<>()));
    }
}
