package ru.dipech.cute.model;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode(of = {"name"})
@AllArgsConstructor
public class Task implements Comparable<Task> {
    private final String name;
    private String title;
    private String description;

    @Override
    public int compareTo(Task task) {
        return this.getName().compareTo(task.getName());
    }
}
