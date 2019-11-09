package ru.dipech.cute.util;

import ru.dipech.cute.model.ScanPath;
import ru.dipech.cute.model.task.Task;

import java.util.Set;
import java.util.stream.Collectors;

public class ScanPathTestUtil {
    public static Set<String> getCmdNames(ScanPath scanPath) {
        return scanPath.getTasks().stream().map((Task::getName)).collect(Collectors.toSet());
    }
}
