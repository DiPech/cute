package ru.dipech.cute.service.parser.input;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Parse cute flag or param to String[]
 */
@Service
@Qualifier("definition-line-parse-strategy")
public class DefinitionLineParseStrategy extends ParseStrategy {
    // See "command-line/readme.txt"
    private final String regex = "(([a-z0-9]+=\\[.*?])|([a-z0-9]+=[a-z0-9\\-]+)|([a-z0-9]+=\".*?\")|([a-z0-9]+))(\\s+)?";
    private final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
