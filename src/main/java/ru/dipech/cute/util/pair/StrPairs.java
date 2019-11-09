package ru.dipech.cute.util.pair;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class StrPairs {
    private final List<StrPair> pairs;

    public void add(StrPair pair) {
        pairs.add(pair);
    }

    public String getOne(String key) {
        StrPair pair = pairs.stream()
            .filter(p -> p.getKey().equals(key))
            .findFirst().orElse(new StrPair(key, null));
        return pair.getValue();
    }

    public List<String> getAll(String key) {
        return pairs.stream()
            .filter(pair -> pair.getKey().equals(key))
            .map(Pair::getValue).collect(Collectors.toList());
    }

    public boolean has(String key) {
        return pairs.stream().anyMatch(p -> p.getKey().equals(key));
    }
}
