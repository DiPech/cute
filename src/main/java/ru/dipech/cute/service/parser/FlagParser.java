package ru.dipech.cute.service.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.parse.FlagParseException;
import ru.dipech.cute.model.task.Flag;
import ru.dipech.cute.service.parser.input.ParseStrategy;
import ru.dipech.cute.util.pair.StrPairs;

@Service
public class FlagParser extends ArgParser<FlagParseException> {
    public FlagParser(@Qualifier("definition-line-parse-strategy") ParseStrategy parseStrategy) {
        super(parseStrategy);
    }

    public Flag parse(String raw) {
        StrPairs strPairs = inputStringStrPairsParser.parse(raw);
        return Flag.builder()
            .name(parseName(strPairs))
            .title(strPairs.getOne("title"))
            .shortcut(parseShortcut(strPairs))
            .build();
    }

    @Override
    protected FlagParseException getException(String message) {
        return new FlagParseException(message);
    }
}
