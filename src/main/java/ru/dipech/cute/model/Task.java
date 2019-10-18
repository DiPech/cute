package ru.dipech.cute.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode(of = {"name"})
@RequiredArgsConstructor
public class Task implements Comparable<Task> {
    private final String name;
    private String title;
    private String description;
    private List<ParamArg> params = new LinkedList<>();
    private List<FlagArg> flags = new LinkedList<>();

    @Override
    public int compareTo(Task task) {
        return this.getName().compareTo(task.getName());
    }
}
