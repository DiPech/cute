package ru.dipech.cute.model.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.dipech.cute.model.ScanPath;
import ru.dipech.cute.model.input.InputTask;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor()
public class TaskContext {
    private final List<InputTask> inputTasks;
    private final Set<ScanPath> scanPaths = new LinkedHashSet<>();

    public void addScanPath(ScanPath scanPath) {
        this.scanPaths.add(scanPath);
    }
}
