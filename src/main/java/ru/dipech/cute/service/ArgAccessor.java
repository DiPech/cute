package ru.dipech.cute.service;

import org.springframework.stereotype.Service;

@Service
public interface ArgAccessor {
    int flagsCount();

    int paramsCount();

    boolean hasFlag(String name);

    boolean hasParam(String name);

    String getParam(String name);
}
