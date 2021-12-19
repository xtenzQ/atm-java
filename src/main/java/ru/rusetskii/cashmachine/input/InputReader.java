package ru.rusetskii.cashmachine.input;

import ru.rusetskii.cashmachine.output.OutputException;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Provides console input
 */
public class InputReader implements InputSystem {
    private Scanner in;

    /**
     * Creates console input object
     * Initializes new {@link Scanner} since it's console input
     */
    public InputReader(InputStream stream) {
        in = new Scanner(stream).useDelimiter("\r\n");
    }

    /**
     * Read command from the input
     *
     * @return command
     */
    @Override
    public String input() {
        if (inputAvailable()) {
            return in.nextLine();
        }
        return null;
    }

    @Override
    public void close() throws OutputException {
        in.close();
    }

    @Override
    public boolean inputAvailable() {
        if (in.hasNextLine()) {
            return true;
        }
        return false;
    }
}
