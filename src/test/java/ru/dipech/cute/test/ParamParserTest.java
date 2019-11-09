package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.parse.ParamParseException;
import ru.dipech.cute.model.task.Param;
import ru.dipech.cute.service.parser.ParamParser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ParamParserTest {

    @Autowired
    private ParamParser parser;

    @Test
    void parseWithName() {
        Param expected = getCommonBuilder().build();
        assertEquals(expected, parser.parse(getCommonString()));
    }

    @Test
    void parseWithNameAndTitle() {
        Param expected = getCommonBuilder().title("tit").build();
        assertEquals(expected, parser.parse(getCommonString() + "title=\"tit\""));
    }

    @Test
    void parseWithNameAndShortcut() {
        Param expected = getCommonBuilder().shortcut('n').build();
        assertEquals(expected, parser.parse(getCommonString() + "shortcut=n"));
    }

    @Test
    void parseWithNameAndMultiple() {
        Param expected = getCommonBuilder().canHaveMultipleValues(true).build();
        assertEquals(expected, parser.parse(getCommonString() + "multiple"));
    }

    @Test
    void parseWithNameAndRequired() {
        Param expected = getCommonBuilder().required(true).build();
        assertEquals(expected, parser.parse(getCommonString() + "required"));
    }

    @Test
    void parseWithNameAndPattern() {
        Param expected = getCommonBuilder().validationPattern("[a-z0-9]+").build();
        assertEquals(expected, parser.parse(getCommonString() + "validate=\"[a-z0-9]+\""));
    }

    @Test
    void parseWithNameAndDefault() {
        List<String> defVal = new ArrayList<>();
        defVal.add("a");
        defVal.add("bb");
        defVal.add("ccc");
        Param expected = getCommonBuilder().defaultValue(defVal).build();
        assertEquals(expected, parser.parse(getCommonString() + "default=[\"a\", \"bb\", \"ccc\"]"));
    }

    @Test
    void parseWithoutName() {
        assertThrows(ParamParseException.class, () -> parser.parse("test=\"just for test\""));
    }

    @Test
    void parseWithEmptyName() {
        assertThrows(ParamParseException.class, () -> parser.parse("name=\"\""));
    }

    @Test
    void parseWithMalformedShortcut() {
        assertThrows(ParamParseException.class, () -> parser.parse(getCommonString() + "shortcut=\"nm\""));
    }

    private Param.ParamBuilder getCommonBuilder() {
        return Param.builder().name("name");
    }

    private String getCommonString() {
        return "name=\"name\" ";
    }
}
