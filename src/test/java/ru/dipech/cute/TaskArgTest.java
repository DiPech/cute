package ru.dipech.cute;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.TaskArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TaskArgTest {
    @Test
    void parseNonTask() {
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("-f"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("--flag"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("-p=v"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("--param=val"));
    }

    @Test
    void parseMalformedTask() {
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse(":asdasd"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("asdasd:"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse(":"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("tAsK:NaMe"));
        assertThrows(ArgParseException.class, () -> (new TaskArg()).parse("task::name"));
    }

    @Test
    void parseSimpleTask() {
        assertEquals(new TaskArg("task"), (new TaskArg()).parse("task"));
    }

    @Test
    void parseDifficultTask() {
        assertEquals(new TaskArg("task:with:colons"), (new TaskArg()).parse("task:with:colons"));
    }
}
