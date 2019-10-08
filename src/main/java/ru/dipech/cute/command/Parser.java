package ru.dipech.cute.command;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    public Command parse(Path file, List<String> components) {
        return Command.builder()
                .name(getCommandName(file, components))
                .script(file)
                .build();
    }

    private String getCommandName(Path file, List<String> components) {
        String fileName = file.getFileName().toString();
        String fileBaseName = FilenameUtils.removeExtension(fileName);
        List<String> nameComponents = new LinkedList<>(components);
        nameComponents.add(fileBaseName);
        return StringUtils.join(nameComponents, ":");
    }
}
