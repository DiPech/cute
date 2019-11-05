package ru.dipech.cute.service.validator;

import org.apache.commons.lang3.StringUtils;
import ru.dipech.cute.exception.InternalException;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator<T, E extends InternalException> {
    private List<String> errors = new ArrayList<>();

    public void validate(T t) {
        errors.clear();
        doValidate(t);
        if (errors.size() > 0) {
            throwException();
        }
    }

    protected void addError(String message) {
        errors.add(message);
    }

    private void throwException() {
        throw getException(StringUtils.join(errors, " "));
    }

    protected abstract void doValidate(T t);

    protected abstract E getException(String message);
}
