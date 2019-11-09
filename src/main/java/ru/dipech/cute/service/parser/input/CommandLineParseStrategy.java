package ru.dipech.cute.service.parser.input;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Parse single commandline like string to array (e.g. like bash does)
 */
@Service
@Primary
@Qualifier("command-line-parse-strategy")
public class CommandLineParseStrategy extends ParseStrategy {
    // See "command-line/readme.txt"
    private final String regex = "((--[\\w\\d]+=\".*?\")|(-[\\w\\d]=\".*?\")|(--[\\w\\d]+=[\\w\\d-_]+)|" +
        "(-[\\w\\d]=[\\w\\d-_]+)|(--[\\w\\d]+)|(-[\\w\\d]+)|(-[\\w\\d])|([\\w\\d:]+))(\\s+)?";
    private final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
