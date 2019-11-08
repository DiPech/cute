package ru.dipech.cute.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dipech.cute.service.parser.TaskFileParser;
import ru.dipech.cute.util.Pair;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dipech.cute.util.TestUtil.TASK_DATA_PATH;

@SpringBootTest
class TaskFileParserTest {

    @Autowired
    private TaskFileParser parser;

    @Test
    void parseFile() {
        List<Pair<String, String>> expected = new ArrayList<>();
        expected.add(new Pair<>("title", "Some title"));
        expected.add(new Pair<>("description", StringUtils.join(
            "Some so long and",
            " multiline description.",
            "  It's also can be super multilined."
        )));
        expected.add(new Pair<>("exec-before", "minimum     minimum minimum"));
        expected.add(new Pair<>("exec-after", "minimum        minimum                  minimum"));
        expected.add(new Pair<>("flag", "name=print shortcut=p title=\"Print something\""));
        expected.add(new Pair<>("flag", "name=d title=\"Draw something\""));
        expected.add(new Pair<>("param", "name=s title=\"Defines some scope\" required validate=\"[a-z]+\""));
        expected.add(new Pair<>("param", StringUtils.join(
            "name=multi title=\"Parameter can accepts multiple values\"",
            "        default=[\"a\", \"b\", \"c\"] validate=“[a-z]\" multiple"
        )));
        assertEquals(expected, parser.parse(Paths.get(TASK_DATA_PATH + "/file-parser.sh")));
    }
}
