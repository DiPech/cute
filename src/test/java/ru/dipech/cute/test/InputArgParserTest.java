package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.InputArgParser;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.ParamInputArg;
import ru.dipech.cute.model.input.TaskInputArg;

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
        String[] input = new String[]{"-v", "-p=v", "task:name"};
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseDifficultInput() {
        List<InputArg> expected = new LinkedList<>();
        expected.add(new FlagInputArg("task"));
        expected.add(new FlagInputArg("a"));
        expected.add(new FlagInputArg("b"));
        expected.add(new FlagInputArg("c"));
        expected.add(new ParamInputArg("param", "value"));
        expected.add(new FlagInputArg("d"));
        expected.add(new FlagInputArg("e"));
        expected.add(new TaskInputArg("another:name"));
        expected.add(new FlagInputArg("flag"));
        String[] input = new String[]{"task", "-abc", "--param=value", "-de", "another:name", "--flag"};
        assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseEmptyInput() {
        List<InputArg> expected = new LinkedList<>();
        String[] input = new String[]{};
        assertEquals(expected, parser.parse(input));
    }
}
