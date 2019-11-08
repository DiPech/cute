package ru.dipech.cute.service.parser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.TaskParseException;
import ru.dipech.cute.util.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TaskFileParser {
    private final Pattern definitionPrefixPattern = Pattern.compile("^#![a-z\\-]+:");
    private final Pattern definitionParserPattern = Pattern.compile("^(.*?):\\s?(.*)$");

    public List<Pair<String, String>> parse(Path filePath) {
        List<String> definitionLines = getDefinitionLines(filePath);
        List<String> definitions = getDefinitions(definitionLines);
        return getDefinitionPairs(definitions);
    }

    private List<String> getDefinitionLines(Path filePath) {
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(filePath)) {
            boolean isFirstLine = true;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!StringUtils.startsWith(line, "#")) {
                    break;
                }
                if (isFirstLine) {
                    isFirstLine = false;
                    if (line.contains("/bin/")) {
                        continue;
                    }
                }
                result.add(line);
            }
        } catch (IOException e) {
            throw new TaskParseException(e);
        }
        return result;
    }

    private List<String> getDefinitions(List<String> lines) {
        List<String> result = new ArrayList<>();
        StringBuilder definitionBuilder = null;
        for (String line : lines) {
            if (isDefinitionFirstLine(line)) {
                if (definitionBuilder != null) {
                    result.add(definitionBuilder.toString());
                }
                definitionBuilder = new StringBuilder();
            }
            if (definitionBuilder == null) {
                continue;
            }
            definitionBuilder.append(line.substring(1));
        }
        if (definitionBuilder != null && definitionBuilder.length() > 0) {
            result.add(definitionBuilder.toString());
        }
        return result;
    }

    private List<Pair<String, String>> getDefinitionPairs(List<String> definitions) {
        return definitions.stream()
            .map(line -> StringUtils.stripStart(line, "#!"))
            .map(this::parseDefinitionPair)
            .collect(Collectors.toList());
    }

    private boolean isDefinitionFirstLine(String line) {
        Matcher matcher = definitionPrefixPattern.matcher(line);
        return matcher.find();
    }

    private Pair<String, String> parseDefinitionPair(String line) {
        Matcher matcher = definitionParserPattern.matcher(line);
        if (!matcher.find()) {
            throw new TaskParseException("Can't parse task definition line: " + line);
        }
        return new Pair<>(matcher.group(1), matcher.group(2));
    }
}
