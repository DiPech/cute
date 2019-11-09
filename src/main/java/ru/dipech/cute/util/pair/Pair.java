package ru.dipech.cute.util.pair;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Pair<T, K> {
    private final T key;
    private K value;
}
