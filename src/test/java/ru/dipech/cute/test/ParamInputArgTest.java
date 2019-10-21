package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.input.ParamInputArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ParamInputArgTest {
    @Test
    void parseNonParam() {
        assertThrows(ArgParseException.class, () -> (new ParamInputArg()).parse("nonparam"));
    }

    @Test
    void parseMalformedParam() {
        assertThrows(ArgParseException.class, () -> (new ParamInputArg()).parse("-f"));
        assertThrows(ArgParseException.class, () -> (new ParamInputArg()).parse("--flag"));
        assertThrows(ArgParseException.class, () -> (new ParamInputArg()).parse("task:name"));
        assertThrows(ArgParseException.class, () -> (new ParamInputArg()).parse("=asdasdasd"));
    }

    @Test
    void parseParamWithEmptyValue() {
        assertEquals(new ParamInputArg("par", ""), (new ParamInputArg()).parse("--par="));
    }

    @Test
    void parseSimpleParam() {
        assertEquals(new ParamInputArg("n", "v"), (new ParamInputArg()).parse("-n=v"));
    }

    @Test
    void parseDifficultParam() {
        String name = "param";
        String value = "so_me va!lu123e - with : many =\"ot'()'her\"  symbols";
        assertEquals(new ParamInputArg(name, value), (new ParamInputArg()).parse(name + "=" + value));
    }
}
