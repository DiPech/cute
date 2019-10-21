package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.input.TaskInputArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TaskInputArgTest {
    @Test
    void parseNonTask() {
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("-f"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("--flag"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("-p=v"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("--param=val"));
    }

    @Test
    void parseMalformedTask() {
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse(":asdasd"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("asdasd:"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse(":"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("tAsK:NaMe"));
        assertThrows(ArgParseException.class, () -> (new TaskInputArg()).parse("task::name"));
    }

    @Test
    void parseSimpleTask() {
        assertEquals(new TaskInputArg("task"), (new TaskInputArg()).parse("task"));
    }

    @Test
    void parseDifficultTask() {
        assertEquals(new TaskInputArg("task:with:colons"), (new TaskInputArg()).parse("task:with:colons"));
    }
}
