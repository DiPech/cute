package ru.dipech.cute.service.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.parse.ParamParseException;
import ru.dipech.cute.model.task.Param;
import ru.dipech.cute.service.parser.input.InputSequenceParser;
import ru.dipech.cute.service.parser.input.ParseStrategy;
import ru.dipech.cute.util.pair.StrPairs;

import java.util.Collections;
import java.util.List;

@Service
public class ParamParser extends ArgParser<ParamParseException> {
    private final InputSequenceParser inputSequenceParser;

    public ParamParser(
        @Qualifier("definition-line-parse-strategy") ParseStrategy parseStrategy,
        InputSequenceParser inputSequenceParser
    ) {
        super(parseStrategy);
        this.inputSequenceParser = inputSequenceParser;
    }

    public Param parse(String raw) {
        StrPairs strPairs = inputStringStrPairsParser.parse(raw);
        return Param.builder()
            .name(parseName(strPairs))
            .title(strPairs.getOne("title"))
            .shortcut(parseShortcut(strPairs))
            .canHaveMultipleValues(strPairs.has("multiple"))
            .required(strPairs.has("required"))
            .validationPattern(strPairs.getOne("validate"))
            .defaultValue(parseDefaultValue(strPairs))
            .build();
    }

    @Override
    protected ParamParseException getException(String message) {
        return new ParamParseException(message);
    }

    private List<String> parseDefaultValue(StrPairs strPairs) {
        String defValues = strPairs.getOne("default");
        if (defValues == null) {
            return null;
        }
        if (!defValues.startsWith("[") && !defValues.endsWith("]")) {
            return Collections.singletonList(defValues);
        }
        return inputSequenceParser.parse(defValues);
    }
}
