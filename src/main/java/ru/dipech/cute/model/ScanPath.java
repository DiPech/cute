package ru.dipech.cute.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = {"path"})
@RequiredArgsConstructor
public class ScanPath {
    private final Path path;
    private String name;
    private Set<Task> tasks = new HashSet<>();
}