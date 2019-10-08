package ru.dipech.cute.scanner;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.command.Command;
import ru.dipech.cute.command.Parser;
import ru.dipech.cute.exception.InternalException;
import ru.dipech.cute.log.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Scanner {
    @Getter
    private final Set<Command> commands = new HashSet<>();
    private final Parser parser = new Parser();
    private final Logger logger = Logger.getInstance();

    public void scan(Path path) {
        logger.log("Scanning for path '" + path + "'");
        scanInternal(path, new LinkedList<>());
        logger.log("Parsed: " + commands.size());
    }

    public void scan(List<Path> paths) {
        logger.log("Scanning for paths " + paths);
        paths.forEach(this::scan);
    }

    private void scanInternal(Path path, List<String> components) {
        if (!Files.exists(path)) {
            logger.log("Path not exists: " + path);
            return;
        }
        if (!Files.isReadable(path)) {
            logger.log("Path not readable: " + path);
            return;
        }
        try {
            Files.list(path).forEach(p -> {
                if (Files.isDirectory(p)) {
                    List<String> pComponents = new LinkedList<>(components);
                    pComponents.add(p.getFileName().toString());
                    scanInternal(p, pComponents);
                } else if (isPathCanBeParsed(p)) {
                    Command command = parser.parse(p, components);
                    commands.add(command);
                }
            });
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    private boolean isPathCanBeParsed(Path path) {
        if (!Files.isRegularFile(path)) {
            return false;
        }
        String fileName = path.getFileName().toString();
        return StringUtils.endsWith(fileName, ".sh");
    }
}
