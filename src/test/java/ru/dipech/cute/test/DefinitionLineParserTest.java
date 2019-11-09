package ru.dipech.cute.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.input.InputStringParser;
import ru.dipech.cute.service.parser.input.ParseStrategy;

import java.util.Arrays;

import static ru.dipech.cute.util.TestUtil.COMMAND_LINE_DATA_PATH;
import static ru.dipech.cute.util.TestUtil.getFileContent;

@SpringBootTest
class DefinitionLineParserTest {

    @Autowired
    @Qualifier("definition-line-parse-strategy")
    private ParseStrategy parseStrategy;

    @Test
    void parseDefinition() {
        InputStringParser parser = new InputStringParser(parseStrategy);
        // see "command-line/readme.txt"
        String[] expected = new String[]{
            "name=multi", "title=Parameter can accepts multiple values",
            "default=[\"a\", \"b\", \"c\"]", "validate=[a-z]", "multiple"
        };
        String command = getFileContent(COMMAND_LINE_DATA_PATH + "/definition.txt");
        Assertions.assertEquals(Arrays.asList(expected), Arrays.asList(parser.parse(command)));
    }
}
