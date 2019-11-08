package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.exception.ScanPathParseException;
import ru.dipech.cute.service.parser.ScanPathParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.dipech.cute.util.ScanPathTestUtil.getCmdNames;
import static ru.dipech.cute.util.TestUtil.PARSER_DATA_PATH;
import static ru.dipech.cute.util.TestUtil.getSet;

@SpringBootTest
class ScanPathParserTest {
    @Autowired
    private ScanPathParser parser;

    @Test
    void parseMultilevel() {
        assertEquals(getSet(
            "aaa", "bbb", "ddd",
            "bbb:aaa", "bbb:eee", "bbb:fff:mmm", "bbb:fff:rrr",
            "ccc:aaa", "ccc:bbb"
        ), getCmdNames(parser.parse(PARSER_DATA_PATH + "/multi-level")));
    }

    @Test
    void parseCollision() {
        assertEquals(getSet(
            "ccc:aaa", "bbb"
        ), getCmdNames(parser.parse(PARSER_DATA_PATH + "/collision")));
    }

    @Test
    void parseMalformed() {
        assertEquals(getSet(), getCmdNames(parser.parse(PARSER_DATA_PATH + "/malformed")));
    }

    @Test
    void parseWithTrash() {
        assertEquals(getSet(
            "task", "long-name-task", "parent-name:child-name"
        ), getCmdNames(parser.parse(PARSER_DATA_PATH + "/with-trash")));
    }

    @Test
    void parseNonExistentPath() {
        assertThrows(ScanPathParseException.class, () -> parser.parse(PARSER_DATA_PATH + "/non-existent-path"));
    }

    @Test
    void parseNonDirectoryPath() {
        assertThrows(ScanPathParseException.class, () -> parser.parse(PARSER_DATA_PATH + "/with-trash/archive.zip"));
    }

    @Test
    void parseOutsideUserHomeDirectoryPath() {
        assertThrows(ScanPathParseException.class, () -> parser.parse("/"));
    }
}
