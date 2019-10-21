package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.input.FlagInputArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FlagInputArgTest {
    @Test
    void parseNonFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("nonflagstr"));
    }

    @Test
    void parseFlagWithTooManyDashes() {
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("---flag"));
    }

    @Test
    void parseShortButLongFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("-flag"));
    }

    @Test
    void parsLongButShortFlag() {
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("--f"));
    }

    @Test
    void parseFlagWithBadSymbols() {
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("-p=test"));
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("--parameter=test"));
        assertThrows(ArgParseException.class, () -> (new FlagInputArg()).parse("task:name"));
    }

    @Test
    void parseShortFlag() {
        assertEquals(new FlagInputArg("f"), (new FlagInputArg()).parse("-f"));
    }

    @Test
    void parseLongFlag() {
        assertEquals(new FlagInputArg("flag"), (new FlagInputArg()).parse("--flag"));
    }
}
