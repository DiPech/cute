package ru.dipech.cute.scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ScannerUtil {
    /**
     * Return paths parsed form CUTE_PATH env and current path + "/cute" suffix
     */
    public static List<Path> getPathsToScan() {
        Set<String> paths = new HashSet<>();
        String envPathsStr = System.getenv("CUTE_PATH");
        if (envPathsStr != null) {
            if (envPathsStr.contains(":")) {
                String[] envPaths = envPathsStr.split(":");
                paths.addAll(Arrays.asList(envPaths));
            }
        }
        paths.add(System.getProperty("user.dir") + "/cute");
        return paths.stream().map(str -> Paths.get(str)).collect(Collectors.toList());
    }
}
