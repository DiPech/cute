package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.parse.ParseException;
import ru.dipech.cute.model.task.Arg;
import ru.dipech.cute.service.parser.input.InputStringParser;
import ru.dipech.cute.service.parser.input.InputStringStrPairsParser;
import ru.dipech.cute.service.parser.input.ParseStrategy;
import ru.dipech.cute.util.pair.StrPairs;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public abstract class ArgParser<E extends ParseException> {
    private final ParseStrategy parseStrategy;

    protected InputStringStrPairsParser inputStringStrPairsParser;

    @PostConstruct
    private void init() {
        InputStringParser inputStringParser = new InputStringParser(parseStrategy);
        inputStringStrPairsParser = new InputStringStrPairsParser(inputStringParser);
    }

    protected String parseName(StrPairs strPairs) {
        if (!strPairs.has("name") || strPairs.getOne("name") == null) {
            throw getException("Attribute «name» is required.");
        }
        return strPairs.getOne("name");
    }

    protected Character parseShortcut(StrPairs strPairs) {
        String shortcutStr = strPairs.getOne("shortcut");
        if (shortcutStr != null && shortcutStr.length() > 1) {
            throw getException("Given shortcut «" + shortcutStr + "» is too long.");
        }
        return shortcutStr != null ? shortcutStr.charAt(0) : null;
    }

    public abstract Arg parse(String raw);

    protected abstract E getException(String message);
}
