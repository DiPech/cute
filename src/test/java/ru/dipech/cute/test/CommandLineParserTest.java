package ru.dipech.cute.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.input.InputStringParser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.COMMAND_LINE_DATA_PATH;
import static ru.dipech.cute.util.TestUtil.getFileContent;

@SpringBootTest
class CommandLineParserTest {

    @Autowired
    private InputStringParser parser;

    @Test
    void parseCommand() {
        // see "command-line/readme.txt"
        List<String> expected = Arrays.asList(
            "cute", "-v", "-i", "--azaza", "-g=d", "--oloo=pepepe", "-e=", "--empty=", "task1",
            "--plot=twist with spaces", "-a=as12", "--bb=bf-g2_3", "-g", "--gfds", "sub:task", "sub:sub:task",
            "-vim", "--smoke=scope", "azaza:task",
            "--param=any data with:unavail \t\tparams -a -asd \t\t\t--asdasd -s=dass --asdas='asdasd'",
            "-g"
        );
        String command = getFileContent(COMMAND_LINE_DATA_PATH + "/command.txt");
        assertEquals(expected, parser.parse(command));
    }
}
