package ru.dipech.cute.input;

import org.springframework.stereotype.Component;
import ru.dipech.cute.model.Arg;

import java.util.LinkedList;
import java.util.List;

@Component
public class Parser {
    public List<Arg> parse(String[] input) {
        return new LinkedList<>();
    }
}
