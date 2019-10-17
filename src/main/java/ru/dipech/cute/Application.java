package ru.dipech.cute;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dipech.cute.state.State;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    // We're using custom arguments syntax, that incompatible with Spring, avoid passing args to app.run();
    private static String[] inputArguments;

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
        String[] args = inputArguments != null ? inputArguments : new String[]{};
        for (String argument : args) {
            System.out.println(argument);
        }
        State state = State.getInstance();
        state.execute();
    }
}
