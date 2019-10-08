package ru.dipech.cute.state;

import ru.dipech.cute.command.Command;
import ru.dipech.cute.command.Executor;
import ru.dipech.cute.input.CommandArg;
import ru.dipech.cute.input.ParamArg;
import ru.dipech.cute.input.Processor;
import ru.dipech.cute.log.Logger;
import ru.dipech.cute.scanner.ScannerService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExecuteCommandsState extends State {
    public ExecuteCommandsState(Processor processor) {
        super(processor);
    }

    public void execute() {
        Logger logger = Logger.getInstance();
        ScannerService scannerService = new ScannerService();
        Set<Command> commands = scannerService.getCommands();
        Queue<String> commandToExecuteNames = new LinkedList<>();
        processor.getConcreteArgs(CommandArg.class).forEach(commandArg -> commandToExecuteNames.add(commandArg.getValue()));
        Map<String, Command> namesAndCommands = commands.stream()
                .collect(Collectors.toMap(Command::getName, Function.identity()));
        int counter = 1;
        String nicePrefix = "========== [ ";
        String niceSuffix = " ] ============================";
        Executor executor = new Executor();
        List<ParamArg> paramArgs = processor.getConcreteArgs(ParamArg.class);
        while (commandToExecuteNames.size() > 0) {
            String commandToExecuteName = commandToExecuteNames.poll();
            if (!namesAndCommands.containsKey(commandToExecuteName)) {
                logger.log("Command not found: " + commandToExecuteName);
                continue;
            }
            Command command = namesAndCommands.get(commandToExecuteName);
            System.out.println(nicePrefix + "Step #" + counter + " - " + commandToExecuteName + niceSuffix);
            counter++;
            executor.execute(command, paramArgs);
            System.out.println();
        }
    }
}
