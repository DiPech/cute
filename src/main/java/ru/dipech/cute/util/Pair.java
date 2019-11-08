package ru.dipech.cute.util;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pair<T, K> {
    private T key;
    private K value;
}
