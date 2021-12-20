package ru.rusetskii.cashmachine.input;

import ru.rusetskii.cashmachine.output.OutputException;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Provides stream input
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class InputReader implements InputSystem {
    private final Scanner in;

    /**
     * Creates input stream object Initializes new {@link Scanner}
     *
     * @param stream input stream-
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

    /**
     * Closes the stream
     *
     * @throws OutputException if error occurred
     */
    @Override
    public void close() throws OutputException {
        in.close();
    }

    /**
     * Checks next command availability
     *
     * @return <code>true</code> if exists; <code>false</code> otherwise.
     */
    @Override
    public boolean inputAvailable() {
        return in.hasNextLine();
    }
}
