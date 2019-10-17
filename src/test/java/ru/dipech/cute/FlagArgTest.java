package ru.dipech.cute;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.FlagArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FlagArgTest {
    @Test
    void parseNonFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("nonflagstr"));
    }

    @Test
    void parseFlagWithTooManyDashes() {
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("---flag"));
    }

    @Test
    void parseShortButLongFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("-flag"));
    }

    @Test
    void parsLongButShortFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("--f"));
    }

    @Test
    void parseFlagWithBadSymbols() {
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("-p=test"));
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("--parameter=test"));
        assertThrows(ArgParseException.class, () -> (new FlagArg()).parse("task:name"));
    }

    @Test
    void parseShortFlag() {
        assertEquals(new FlagArg("f"), (new FlagArg()).parse("-f"));
    }

    @Test
    void parseLongFlag() {
        assertEquals(new FlagArg("flag"), (new FlagArg()).parse("--flag"));
    }
}
