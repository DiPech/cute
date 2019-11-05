package ru.dipech.cute.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.dipech.cute.model.ScanPath;
import ru.dipech.cute.model.TaskContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TaskContextMappingBuilder {
    @Getter
    private Set<String> collidedTaskNames = new HashSet<>();

    public Map<String, ScanPath> build(TaskContext taskContext) {
        collidedTaskNames.clear();
        Map<String, ScanPath> result = new HashMap<>();
        Set<ScanPath> scanPaths = taskContext.getScanPaths();
        scanPaths.forEach(scanPath -> scanPath.getTasks().forEach(task -> {
            String taskName = task.getName();
            if (result.containsKey(taskName) && !collidedTaskNames.contains(taskName)) {
                collidedTaskNames.add(taskName);
                return;
            }
            result.put(taskName, scanPath);
        }));
        return result;
    }
}
