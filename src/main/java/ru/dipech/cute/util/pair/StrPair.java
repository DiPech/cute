package ru.dipech.cute.util.pair;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrPair extends Pair<String, String> {
    public StrPair(String key) {
        super(key);
    }

    public StrPair(String key, String value) {
        super(key, value);
    }
}
