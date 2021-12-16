package ru.rusetskii.input;

import java.util.Scanner;

/**
 * Provides console input
 */
public class ConsoleInput implements InputSystem {

    private final Scanner stream;

    public ConsoleInput() {
        this.stream = new Scanner(System.in);
    }

    /**
     * Read command from the input
     *
     * @return command
     */
    @Override
    public String input() {
        return stream.nextLine();
    }
}
