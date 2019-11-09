package ru.dipech.cute.service.parser.input;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InputSequenceParser {
    private final String regex = "\"(.+?)\"|([^\\s,\"]+\\s*)";
    private final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    public List<String> parse(String sequence) {
        sequence = StringUtils.strip(sequence, "[]");
        List<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(sequence);
        while (matcher.find()) {
            String value = matcher.group(2);
            if (value == null) {
                value = matcher.group(1);
            }
            result.add(value);
        }
        return result;
    }
}
