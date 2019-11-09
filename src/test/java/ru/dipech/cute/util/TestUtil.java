package ru.dipech.cute.util;

import ru.dipech.cute.exception.InternalException;
import ru.dipech.cute.model.input.FlagInputArg;
import ru.dipech.cute.model.input.InputArg;
import ru.dipech.cute.model.input.ParamInputArg;
import ru.dipech.cute.model.input.TaskInputArg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {
    public static final String RESOURCES_PATH = "src/test/resources";
    public static final String PARSER_DATA_PATH = RESOURCES_PATH + "/parser";
    public static final String TASK_DATA_PATH = RESOURCES_PATH + "/task";

    public static List<InputArg> getInputArgList() {
        List<InputArg> result = new LinkedList<>();
        result.add(new FlagInputArg("u"));
        result.add(new FlagInputArg("unused"));
        result.add(new FlagInputArg("v"));
        result.add(new ParamInputArg("notusedparam", "notusedvalue"));
        result.add(new TaskInputArg("task1"));
        result.add(new ParamInputArg("param", "value"));
        result.add(new TaskInputArg("task:sub:subtask2"));
        result.add(new FlagInputArg("f"));
        result.add(new ParamInputArg("param3", "value3"));
        result.add(new ParamInputArg("p2", "v2"));
        result.add(new ParamInputArg("p1", "v1"));
        return result;
    }

    @SafeVarargs
    public static <T extends InputArg> Map<String, T> getArgMap(T... args) {
        return Stream.of(args).collect(Collectors.toMap(T::getName, Function.identity()));
    }

    @SafeVarargs
    public static <T> Set<T> getSet(T... values) {
        return Stream.of(values).collect(Collectors.toSet());
    }

    public static String getFileContent(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }
}
