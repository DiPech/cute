package ru.dipech.cute.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.CommandLineParser;

import java.util.Arrays;

import static ru.dipech.cute.util.TestUtil.RESOURCES_PATH;
import static ru.dipech.cute.util.TestUtil.getFileContent;

@SpringBootTest
class CommandLineParserTest {

    @Autowired
    private CommandLineParser parser;

    @Test
    void parseComplexCommand() {
        // see "command-line/readme.txt"
        String[] expected = new String[]{
            "cute", "-v", "-i", "--azaza", "-g=d", "--oloo=pepepe", "-e=", "--empty=", "task1",
            "--plot=twist with spaces", "-a=as12", "--bb=bf-g2_3", "-g", "--gfds", "sub:task", "sub:sub:task",
            "-vim", "--smoke=scope", "azaza:task",
            "--param=any data with:unavail \t\tparams -a -asd \t\t\t--asdasd -s=dass --asdas='asdasd'",
            "-g"
        };
        String command = getFileContent(RESOURCES_PATH + "/command-line/command.sh");
        Assertions.assertEquals(Arrays.asList(expected), Arrays.asList(parser.parse(command)));
    }
}
