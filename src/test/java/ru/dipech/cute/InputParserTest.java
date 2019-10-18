package ru.dipech.cute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.input.Parser;
import ru.dipech.cute.model.Arg;
import ru.dipech.cute.model.FlagArg;
import ru.dipech.cute.model.ParamArg;
import ru.dipech.cute.model.TaskArg;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class InputParserTest {

    @Autowired
    private Parser parser;

    @Test
    void parseSimpleInput() {
        List<Arg> expected = new LinkedList<>();
        expected.add(new FlagArg("v"));
        expected.add(new ParamArg("p", "v"));
        expected.add(new TaskArg("task:name"));
        String[] input = new String[]{"-v", "-p=v", "task:name"};
        Assertions.assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseDifficultInput() {
        List<Arg> expected = new LinkedList<>();
        expected.add(new FlagArg("task"));
        expected.add(new FlagArg("a"));
        expected.add(new FlagArg("b"));
        expected.add(new FlagArg("c"));
        expected.add(new ParamArg("param", "value"));
        expected.add(new FlagArg("d"));
        expected.add(new FlagArg("e"));
        expected.add(new TaskArg("another:name"));
        expected.add(new FlagArg("flag"));
        String[] input = new String[]{"task", "-abc", "--param=value", "-de", "another:name", "--flag"};
        Assertions.assertEquals(expected, parser.parse(input));
    }

    @Test
    void parseEmptyInput() {
        List<Arg> expected = new LinkedList<>();
        String[] input = new String[]{};
        Assertions.assertEquals(expected, parser.parse(input));
    }
}
