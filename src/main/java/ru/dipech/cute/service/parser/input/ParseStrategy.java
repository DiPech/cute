package ru.dipech.cute.service.parser.input;

import java.util.regex.Pattern;

public abstract class ParseStrategy {
    public abstract Pattern getPattern();
}
