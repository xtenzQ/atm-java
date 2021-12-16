package ru.rusetskii.output;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static ru.rusetskii.output.MessageConstants.*;

/**
 * Provides console output
 */
public class ConsoleOutput implements OutputSystem {

    private OutputStream stream;

    public ConsoleOutput(OutputStream stream) {
        this.stream = stream;
    }

    /**
     * Send OK message to the output
     *
     * @throws OutputException if an exception occurred
     */
    @Override
    public void ok() throws OutputException {
        try {
            stream.write(OK.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException exception) {
            throw new OutputException(exception);
        }
    }

    /**
     * Send ERROR message to the output
     *
     * @throws OutputException if an exception occurred
     */
    @Override
    public void error() throws OutputException {
        try {
            stream.write(ERROR.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException exception) {
            throw new OutputException(exception);
        }
    }

    /**
     * Send message to the output
     *
     * @param message information message
     * @throws OutputException if an exception occurred
     */
    @Override
    public void message(String message) throws OutputException {
        try {
            stream.write(message.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException exception) {
            throw new OutputException(exception);
        }
    }
}
