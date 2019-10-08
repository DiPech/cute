package ru.dipech.cute.state;

import ru.dipech.cute.command.Command;
import ru.dipech.cute.input.Processor;
import ru.dipech.cute.scanner.ScannerService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrintCommandsState extends State {
    public PrintCommandsState(Processor processor) {
        super(processor);
    }

    public void execute() {
        ScannerService scannerService = new ScannerService();
        Set<Command> commands = scannerService.getCommands();
        List<Command> sortedByNameCommands = commands.stream().sorted().collect(Collectors.toList());
        sortedByNameCommands.forEach(cmd -> System.out.println(cmd.getName()));
    }
}
