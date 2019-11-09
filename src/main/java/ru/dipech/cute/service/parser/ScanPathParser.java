package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.parse.ScanPathParseException;
import ru.dipech.cute.exception.parse.TaskParseException;
import ru.dipech.cute.model.ScanPath;
import ru.dipech.cute.model.task.Task;
import ru.dipech.cute.service.PathsProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScanPathParser {
    private final PathsProvider pathsProvider;
    private final TaskParser taskParser;

    public ScanPath parse(String absolutePath) {
        Path path = Paths.get(absolutePath);
        validatePath(path);
        ScanPath scanPath = new ScanPath(path);
        parseTasks(scanPath);
        return scanPath;
    }

    private void parseTasks(ScanPath scanPath) {
        Path path = scanPath.getPath();
        parseTasksInternal(scanPath, path, new LinkedList<>());
    }

    private void validatePath(Path path) {
        if (!Files.exists(path)) {
            throw new ScanPathParseException("Path " + path + " not exists");
        }
        if (!Files.isDirectory(path)) {
            throw new ScanPathParseException("Path " + path + " isn't a directory");
        }
        Path home = pathsProvider.getHome();
        if (!StringUtils.startsWith(path.toAbsolutePath().toString(), home.toAbsolutePath().toString())) {
            throw new ScanPathParseException("Path " + path + " can't be located outside your home directory");
        }
        if (!Files.isReadable(path)) {
            throw new ScanPathParseException("Path " + path + " isn't readable");
        }
    }

    private void parseTasksInternal(ScanPath scanPath, Path path, List<String> components) {
        if (!Files.isReadable(path)) {
            return;
        }
        try {
            Files.list(path).forEach(p -> {
                List<String> pComponents = new LinkedList<>(components);
                if (Files.isDirectory(p)) {
                    pComponents.add(p.getFileName().toString());
                    parseTasksInternal(scanPath, p, pComponents);
                } else if (isPathCanBeParsed(p)) {
                    try {
                        Task task = taskParser.parse(p, pComponents);
                        scanPath.addTask(task);
                    } catch (TaskParseException e) {
                        log.warn("Can't parse task: " + e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            throw new ScanPathParseException(e);
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
