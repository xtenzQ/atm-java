package ru.rusetskii.cashmachine.input;

import java.util.Scanner;

/**
 * Provides console input
 */
public class ConsoleInput implements InputSystem {

    private final Scanner stream;

    /**
     * Creates console input object
     * Initializes new {@link Scanner} since it's console input
     */
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
        if (stream.hasNextLine()) {
            return stream.nextLine();
        }
        return null;
    }
}
