package ru.dipech.cute.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.model.input.FlagInputArg;
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
        inputTask = InputTask.builder()
            .flags(getArgMap(new FlagInputArg("f"), new FlagInputArg("flag")))
            .params(getArgMap(
                // Regular
                new ParamInputArg("p", "v"),
                new ParamInputArg("param", "value"),
                // Empty
                new ParamInputArg("empty"),
                // Multiple values
                new ParamInputArg("mp", "v1", "v2", "v3")
            ))
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
    void getParamValue() {
        assertEquals(inputTask.getParamValue("p"), "v");
        assertEquals(inputTask.getParamValue("param"), "value");
        assertNull(inputTask.getParamValue("empty"));
        assertNull(inputTask.getParamValue("badparam"));
        assertNull(inputTask.getParamValue("x"));
    }

    @Test
    void getFlagsCount() {
        assertEquals(inputTask.getFlagsCount(), 2);
    }

    @Test
    void getParamsCount() {
        assertEquals(inputTask.getParamsCount(), 4);
    }

    @Test
    void getParamValuesCount() {
        assertEquals(inputTask.getParamValuesCount("badparam"), 0);
        assertEquals(inputTask.getParamValuesCount("empty"), 0);
        assertEquals(inputTask.getParamValuesCount("param"), 1);
        assertEquals(inputTask.getParamValuesCount("mp"), 3);
    }

    @Test
    void getParamValues() {
        assertEquals(inputTask.getParamValues("badparam").length, 0);
        assertEquals(inputTask.getParamValues("empty").length, 0);
        assertEquals(inputTask.getParamValues("p")[0], "v");
        assertEquals(Arrays.asList(inputTask.getParamValues("mp")), Arrays.asList("v1", "v2", "v3"));
    }
}
