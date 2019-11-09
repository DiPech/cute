package ru.dipech.cute.service.parser;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.dipech.cute.exception.parse.ArgParseException;
import ru.dipech.cute.exception.parse.TaskParseException;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.InputTask;
import ru.dipech.cute.model.task.Flag;
import ru.dipech.cute.model.task.Param;
import ru.dipech.cute.model.task.Task;
import ru.dipech.cute.service.parser.input.InputStringParser;
import ru.dipech.cute.service.validator.TaskNameValidator;
import ru.dipech.cute.util.pair.StrPairs;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskParser {
    private static final TaskNameValidator taskNameValidator = new TaskNameValidator();
    private final TaskFileParser taskFileParser;
    private final InputStringParser commandLineParser;
    private final InputArgParser inputArgParser;
    private final InputTaskParser inputTaskParser;
    private final FlagParser flagParser;
    private final ParamParser paramParser;

    public Task parse(Path taskPath) {
        return parse(taskPath, new LinkedList<>());
    }

    public Task parse(Path taskPath, List<String> components) {
        StrPairs strPairs = taskFileParser.parse(taskPath);
        return Task.builder()
            .name(parseTaskName(taskPath, components))
            .title(strPairs.getOne("title"))
            .description(strPairs.getOne("description"))
            .executeBefore(parseExecuteDependency(strPairs, "exec-before"))
            .executeAfter(parseExecuteDependency(strPairs, "exec-after"))
            .flags(parseFlags(strPairs))
            .params(parseParams(strPairs))
            .build();
    }

    private Map<String, Param> parseParams(StrPairs strPairs) {
        return strPairs.getAll("param").stream()
            .map(paramParser::parse)
            .collect(Collectors.toMap(Param::getName, Function.identity()));
    }

    private Map<String, Flag> parseFlags(StrPairs strPairs) {
        return strPairs.getAll("flag").stream()
            .map(flagParser::parse)
            .collect(Collectors.toMap(Flag::getName, Function.identity()));
    }

    private List<InputTask> parseExecuteDependency(StrPairs strPairs, String defKey) {
        String commandLine = strPairs.getOne(defKey);
        if (commandLine == null) {
            return new LinkedList<>();
        }
        List<String> args = commandLineParser.parse(commandLine);
        List<InputArg> inputArgs = inputArgParser.parse(args);
        return inputTaskParser.parse(inputArgs);
    }

    private String parseTaskName(Path taskPath, List<String> components) {
        String taskName = combineFullTaskName(taskPath, components);
        try {
            taskNameValidator.validate(taskName);
        } catch (ArgParseException e) {
            throw new TaskParseException(e);
        }
        return taskName;
    }

    private String combineFullTaskName(Path taskPath, List<String> components) {
        String fileName = taskPath.getFileName().toString();
        String fileBaseName = FilenameUtils.removeExtension(fileName);
        List<String> nameComponents = new LinkedList<>(components);
        nameComponents.add(fileBaseName);
        return StringUtils.join(nameComponents, ":");
    }
}
