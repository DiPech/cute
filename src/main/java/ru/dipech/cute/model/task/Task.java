package ru.dipech.cute.model.task;

import lombok.*;
import ru.dipech.cute.model.input.InputTask;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Task implements Comparable<Task> {
    private final String name;
    private String title;
    private String description;
    List<InputTask> executeBefore;
    List<InputTask> executeAfter;
    Map<String, Flag> flags;
    Map<String, Param> params;

    @Override
    public int compareTo(Task task) {
        return this.getName().compareTo(task.getName());
    }
}
