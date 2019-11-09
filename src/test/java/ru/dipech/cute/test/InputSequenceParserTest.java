package ru.dipech.cute.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.input.InputSequenceParser;

import java.util.Arrays;
import java.util.List;

import static ru.dipech.cute.util.TestUtil.COMMAND_LINE_DATA_PATH;
import static ru.dipech.cute.util.TestUtil.getFileContent;

@SpringBootTest
class InputSequenceParserTest {

    @Autowired
    private InputSequenceParser parser;

    @Test
    void parseSequence() {
        List<String> expected = Arrays.asList("a", "abc", "b", "SomeWord", "/var/www/user/images/image.txt");
        String sequence = getFileContent(COMMAND_LINE_DATA_PATH + "/sequence.txt");
        Assertions.assertEquals(expected, parser.parse(sequence));
    }
}
