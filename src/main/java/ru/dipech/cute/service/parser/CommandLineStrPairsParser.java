package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.util.pair.StrPair;
import ru.dipech.cute.util.pair.StrPairs;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandLineStrPairsParser {
    private final CommandLineParser commandLineParser;

    public StrPairs parse(String command) {
        String[] args = commandLineParser.parse(command);
        return new StrPairs(Arrays.stream(args)
            .map(this::parseStrPair)
            .collect(Collectors.toList())
        );
    }

    private StrPair parseStrPair(String str) {
        int eqIndex = str.indexOf("=");
        if (eqIndex < 0) {
            return new StrPair(str, null);
        }
        String key = str.substring(0, eqIndex);
        String val = str.substring(eqIndex + 1);
        return new StrPair(key, val.length() > 0 ? val : null);
    }
}
