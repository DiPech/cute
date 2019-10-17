package ru.dipech.cute.state;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class State {

    public static State getInstance() {
        return new PrintAppVersionState();
    }

    public abstract void execute();
}
