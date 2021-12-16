package ru.rusetskii.input;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Provides console input
 */
public class ConsoleInput implements InputSystem {

    private InputStream stream;

    public ConsoleInput(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Read command from the input
     *
     * @return command
     */
    @Override
    public String input() {
        return new Scanner(stream).nextLine();
    }
}
