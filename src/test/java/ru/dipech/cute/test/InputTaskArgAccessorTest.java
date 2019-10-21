package ru.dipech.cute.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.input.ParamInputArg;

import static org.junit.jupiter.api.Assertions.*;
import static ru.dipech.cute.util.TestUtil.getArgMap;

@SpringBootTest
class InputTaskArgAccessorTest {
    private static InputTask inputTask;

    @BeforeAll
    static void init() {
        inputTask = InputTask.builder()
            .flags(getArgMap(new FlagInputArg("f"), new FlagInputArg("flag")))
            .params(getArgMap(new ParamInputArg("p", "v"), new ParamInputArg("param", "value"), new ParamInputArg("empty", null)))
            .build();
    }

    @Test
    void hasFlag() {
        assertTrue(inputTask.hasFlag("f"));
        assertTrue(inputTask.hasFlag("flag"));
        assertFalse(inputTask.hasFlag("badflag"));
        assertFalse(inputTask.hasFlag("x"));
    }

    @Test
    void hasParam() {
        assertTrue(inputTask.hasParam("p"));
        assertTrue(inputTask.hasParam("param"));
        assertTrue(inputTask.hasParam("empty"));
        assertFalse(inputTask.hasParam("badparam"));
        assertFalse(inputTask.hasParam("x"));
    }

    @Test
    void getParam() {
        assertEquals(inputTask.getParam("p"), "v");
        assertEquals(inputTask.getParam("param"), "value");
        assertNull(inputTask.getParam("empty"));
        assertNull(inputTask.getParam("badparam"));
        assertNull(inputTask.getParam("x"));
    }

    @Test
    void flagsCount() {
        assertEquals(inputTask.flagsCount(), 2);
    }

    @Test
    void paramsCount() {
        assertEquals(inputTask.paramsCount(), 3);
    }
}
