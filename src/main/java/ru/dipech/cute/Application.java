package ru.dipech.cute;

import ru.dipech.cute.input.Processor;

public class Application {
    public static void main(String[] args) {
        Processor processor = new Processor();
        try {
            processor.process(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        processor.getArgs().forEach(System.out::println);
        System.out.println("Done.");
    }
}
