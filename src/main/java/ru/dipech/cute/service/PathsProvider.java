package ru.dipech.cute.service;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PathsProvider {
    public Path getHome() {
        return getByString(System.getProperty("user.home"));
    }

    private Path getByString(String path) {
        return Paths.get(path);
    }
}
