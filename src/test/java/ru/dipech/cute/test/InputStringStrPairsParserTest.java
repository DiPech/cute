package ru.dipech.cute.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.input.InputStringStrPairsParser;
import ru.dipech.cute.util.pair.StrPair;
import ru.dipech.cute.util.pair.StrPairs;

import java.util.Arrays;

import static ru.dipech.cute.util.TestUtil.COMMAND_LINE_DATA_PATH;
import static ru.dipech.cute.util.TestUtil.getFileContent;

@SpringBootTest
class InputStringStrPairsParserTest {

    @Autowired
    private InputStringStrPairsParser parser;

    @Test
    void parseComplexCommand() {
        StrPairs expected = new StrPairs(Arrays.asList(
            new StrPair("cute"), new StrPair("-v"), new StrPair("-i"), new StrPair("--azaza"), new StrPair("-g", "d"),
            new StrPair("--oloo", "pepepe"), new StrPair("-e"), new StrPair("--empty"), new StrPair("task1"),
            new StrPair("--plot", "twist with spaces"), new StrPair("-a", "as12"), new StrPair("--bb", "bf-g2_3"),
            new StrPair("-g"), new StrPair("--gfds"), new StrPair("sub:task"), new StrPair("sub:sub:task"),
            new StrPair("-vim"), new StrPair("--smoke", "scope"), new StrPair("azaza:task"),
            new StrPair("--param", "any data with:unavail \t\tparams -a -asd \t\t\t--asdasd -s=dass --asdas='asdasd'"),
            new StrPair("-g")
        ));
        String command = getFileContent(COMMAND_LINE_DATA_PATH + "/command.txt");
        Assertions.assertEquals(expected, parser.parse(command));
    }
}
