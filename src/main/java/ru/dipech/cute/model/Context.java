package ru.dipech.cute.model;

import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
public class Context {
    private final Set<ScanPath> scanPaths = new LinkedHashSet<>();

    public void addScanPath(ScanPath scanPath) {
        this.scanPaths.add(scanPath);
    }
}
