package ru.dipech.cute.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"path"})
@RequiredArgsConstructor
public class ScanPath {
    private final Path path;
    private Set<Task> tasks = new HashSet<>();

    public void addTask(Task task) {
        tasks.add(task);
    }
}
