package ru.dipech.cute.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.TaskFileParser;
import ru.dipech.cute.util.pair.StrPair;
import ru.dipech.cute.util.pair.StrPairs;

import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.TASK_DATA_PATH;

@SpringBootTest
class TaskFileParserTest {

    @Autowired
    private TaskFileParser parser;

    @Test
    void parseFile() {
        StrPairs expected = new StrPairs(new ArrayList<>());
        expected.add(new StrPair("title", "Some title"));
        expected.add(new StrPair("description", StringUtils.join(
            "Some so long and",
            " multiline description.",
            "  It's also can be super multilined."
        )));
        expected.add(new StrPair("exec-before", "minimum     minimum minimum"));
        expected.add(new StrPair("exec-after", "minimum        minimum                  minimum"));
        expected.add(new StrPair("flag", "name=print shortcut=p title=\"Print something\""));
        expected.add(new StrPair("flag", "name=d title=\"Draw something\""));
        expected.add(new StrPair("param", "name=s title=\"Defines some scope\" required validate=\"[a-z]+\""));
        expected.add(new StrPair("param", StringUtils.join(
            "name=multi title=\"Parameter can accepts multiple values\"",
            "        default=[\"a\", \"b\", \"c\"] validate=“[a-z]\" multiple"
        )));
        assertEquals(expected, parser.parse(Paths.get(TASK_DATA_PATH + "/file-parser.sh")));
    }
}
