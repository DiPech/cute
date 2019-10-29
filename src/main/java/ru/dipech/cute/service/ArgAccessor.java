package ru.dipech.cute.service;

import org.springframework.stereotype.Service;

@Service
public interface ArgAccessor {
    int getFlagsCount();

    int getParamsCount();

    int getParamValuesCount(String name);

    boolean hasFlag(String name);

    boolean hasParam(String name);

    String getParamValue(String name);

    String[] getParamValues(String name);
}
