package ru.dipech.cute.service.parser.input;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dipech.cute.util.pair.StrPair;
import ru.dipech.cute.util.pair.StrPairs;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputStringStrPairsParser {
    private final InputStringParser inputStringParser;

    public StrPairs parse(String command) {
        List<String> args = inputStringParser.parse(command);
        return new StrPairs(args.stream()
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
