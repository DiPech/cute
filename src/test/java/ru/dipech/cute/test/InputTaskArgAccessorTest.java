package ru.dipech.cute.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputArgs;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.input.ParamInputArg;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.dipech.cute.util.TestUtil.getArgMap;

@SpringBootTest
class InputTaskArgAccessorTest {
    private static InputTask inputTask;

    @BeforeAll
    static void init() {
        inputTask = new InputTask(null, new InputArgs(
            getArgMap(new FlagInputArg("f"), new FlagInputArg("flag")),
            getArgMap(
                // Regular
                new ParamInputArg("p", "v"),
                new ParamInputArg("param", "value"),
                // Empty
                new ParamInputArg("empty"),
                // Multiple values
                new ParamInputArg("mp", "v1", "v2", "v3")
            )
        ));
    }

    @Test
    void hasFlag() {
        assertTrue(inputTask.getArgs().hasFlag("f"));
        assertTrue(inputTask.getArgs().hasFlag("flag"));
        assertFalse(inputTask.getArgs().hasFlag("badflag"));
        assertFalse(inputTask.getArgs().hasFlag("x"));
    }

    @Test
    void hasParam() {
        assertTrue(inputTask.getArgs().hasParam("p"));
        assertTrue(inputTask.getArgs().hasParam("param"));
        assertTrue(inputTask.getArgs().hasParam("empty"));
        assertFalse(inputTask.getArgs().hasParam("badparam"));
        assertFalse(inputTask.getArgs().hasParam("x"));
    }

    @Test
    void getParamValue() {
        assertEquals(inputTask.getArgs().getParamValue("p"), "v");
        assertEquals(inputTask.getArgs().getParamValue("param"), "value");
        assertNull(inputTask.getArgs().getParamValue("empty"));
        assertNull(inputTask.getArgs().getParamValue("badparam"));
        assertNull(inputTask.getArgs().getParamValue("x"));
    }

    @Test
    void getFlagsCount() {
        assertEquals(inputTask.getArgs().getFlagsCount(), 2);
    }

    @Test
    void getParamsCount() {
        assertEquals(inputTask.getArgs().getParamsCount(), 4);
    }

    @Test
    void getParamValuesCount() {
        assertEquals(inputTask.getArgs().getParamValuesCount("badparam"), 0);
        assertEquals(inputTask.getArgs().getParamValuesCount("empty"), 0);
        assertEquals(inputTask.getArgs().getParamValuesCount("param"), 1);
        assertEquals(inputTask.getArgs().getParamValuesCount("mp"), 3);
    }

    @Test
    void getParamValues() {
        assertEquals(inputTask.getArgs().getParamValues("badparam").size(), 0);
        assertEquals(inputTask.getArgs().getParamValues("empty").size(), 0);
        assertEquals(inputTask.getArgs().getParamValues("p").get(0), "v");
        assertEquals(inputTask.getArgs().getParamValues("mp"), Arrays.asList("v1", "v2", "v3"));
    }
}
