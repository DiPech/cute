package ru.dipech.cute.state;

import ru.dipech.cute.Application;
import ru.dipech.cute.exception.InternalException;
import ru.dipech.cute.model.context.AppContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class PrintAppVersionState extends State {
    public PrintAppVersionState(AppContext appContext) {
        super(appContext);
    }

    public void execute() {
        String appVersion;
        try {
            InputStream is = Application.class.getResourceAsStream("/app.version");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            appVersion = reader.readLine();
        } catch (IOException e) {
            throw new InternalException("Can't read app.version file", e);
        }
        System.out.println("Cute app. Version " + appVersion + ".");
    }
}
