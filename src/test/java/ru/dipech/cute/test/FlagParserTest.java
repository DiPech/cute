package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.parse.FlagParseException;
import ru.dipech.cute.model.task.Flag;
import ru.dipech.cute.service.parser.FlagParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FlagParserTest {

    @Autowired
    private FlagParser parser;

    @Test
    void parseWithName() {
        Flag expected = getCommonBuilder().build();
        assertEquals(expected, parser.parse(getCommonString()));
    }

    @Test
    void parseWithNameAndTitle() {
        Flag expected = getCommonBuilder().title("Some title").build();
        assertEquals(expected, parser.parse(getCommonString() + "title=\"Some title\""));
    }

    @Test
    void parseWithNameAndShortcut() {
        Flag expected = getCommonBuilder().shortcut('n').build();
        assertEquals(expected, parser.parse(getCommonString() + "shortcut=\"n\""));
    }

    @Test
    void parseWithoutName() {
        assertThrows(FlagParseException.class, () -> parser.parse("test=\"just for test\""));
    }

    @Test
    void parseWithEmptyName() {
        assertThrows(FlagParseException.class, () -> parser.parse("name=\"\""));
    }

    @Test
    void parseWithMalformedShortcut() {
        assertThrows(FlagParseException.class, () -> parser.parse(getCommonString() + "shortcut=\"nm\""));
    }

    private Flag.FlagBuilder getCommonBuilder() {
        return Flag.builder().name("name");
    }

    private String getCommonString() {
        return "name=\"name\" ";
    }
}
