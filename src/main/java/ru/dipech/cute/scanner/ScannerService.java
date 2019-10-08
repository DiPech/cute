package ru.dipech.cute.scanner;

import ru.dipech.cute.command.Command;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class ScannerService {
    public Set<Command> getCommands() {
        List<Path> paths = ScannerUtil.getPathsToScan();
        Scanner scanner = new Scanner();
        scanner.scan(paths);
        return scanner.getCommands();
    }
}
