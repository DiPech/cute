package ru.dipech.cute;

import java.util.Arrays;

public class Application {
	public static void main(String[] args) {
		Arrays.stream(args).forEach(System.out::println);
	}
}
