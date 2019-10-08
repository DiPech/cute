package ru.dipech.cute.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;

@Builder
@Getter
@ToString
public class Command implements Comparable<Command> {
    private final Path script;
    private final String name;

    @Override
    public int compareTo(Command command) {
        return name.compareTo(command.name);
    }
}
