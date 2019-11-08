package ru.dipech.cute.service.parser;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse single commandline like string to String[] (e.g. like bash does)
 */
@Service
public class CommandLineParser {
    // Parse entire command string
    private final String regex = "((--[\\w\\d]+=\".*?\")|(-[\\w\\d]=\".*?\")|(--[\\w\\d]+=[\\w\\d-_]+)|" +
        "(-[\\w\\d]=[\\w\\d-_]+)|(--[\\w\\d]+)|(-[\\w\\d]+)|(-[\\w\\d])|([\\w\\d:]+))(\\s+)?";
    private final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    // Parse only parameter
    private final String parseParamRegex = "(.*?)=\"(.*?)\"";
    private final Pattern parseParamPattern = Pattern.compile(parseParamRegex, Pattern.MULTILINE);

    public String[] parse(String command) {
        List<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(command);
        while (matcher.find()) {
            result.add(preprocessFound(matcher.group(1)));
        }
        return result.toArray(new String[]{});
    }

    private String preprocessFound(String found) {
        int eqPos = found.indexOf("=");
        if (eqPos < 0) {
            return found;
        }
        Matcher matcher = parseParamPattern.matcher(found);
        if (!matcher.matches()) {
            return found;
        }
        return matcher.group(1) + "=" + matcher.group(2);
    }
}
