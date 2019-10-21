package ru.dipech.cute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dipech.cute.model.AppContext;
import ru.dipech.cute.service.AppContextCreator;
import ru.dipech.cute.state.State;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    // We're using custom arguments syntax, that incompatible with Spring, avoid passing args to app.run();
    private static String[] inputArguments;

    private final AppContextCreator appContextCreator;

    public static void main(String[] args) {
        inputArguments = args.clone();
        log.info("Start application");
        try {
            SpringApplication app = new SpringApplication(Application.class);
            app.setBannerMode(Banner.Mode.OFF);
            app.run();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
            exit(1);
        }
        log.info("Finish application");
        exit(0);
    }

    @Override
    public void run(String... emptyArgsArray) {
        String[] rawArgs = inputArguments != null ? inputArguments : new String[]{};
        AppContext appContext = appContextCreator.create(rawArgs);
        State state = State.getInstance(appContext);
        state.execute();
    }
}
