package ru.dipech.cute;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ArgParseException;
import ru.dipech.cute.model.ParamArg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ParamArgTest {
    @Test
    void parseNonParam() {
        assertThrows(ArgParseException.class, () -> (new ParamArg()).parse("nonparam"));
    }

    @Test
    void parseMalformedParam() {
        assertThrows(ArgParseException.class, () -> (new ParamArg()).parse("-f"));
        assertThrows(ArgParseException.class, () -> (new ParamArg()).parse("--flag"));
        assertThrows(ArgParseException.class, () -> (new ParamArg()).parse("task:name"));
        assertThrows(ArgParseException.class, () -> (new ParamArg()).parse("=asdasdasd"));
    }

    @Test
    void parseParamWithEmptyValue() {
        assertEquals(new ParamArg("par", ""), (new ParamArg()).parse("--par="));
    }

    @Test
    void parseSimpleParam() {
        assertEquals(new ParamArg("n", "v"), (new ParamArg()).parse("-n=v"));
    }

    @Test
    void parseDifficultParam() {
        String name = "param";
        String value = "so_me va!lu123e - with : many =\"ot'()'her\"  symbols";
        assertEquals(new ParamArg(name, value), (new ParamArg()).parse(name + "=" + value));
    }
}
