package ru.rusetskii.cashmachine.output;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static ru.rusetskii.cashmachine.output.MessageConstants.*;

/**
 * Provides console output
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class OutputWriter implements OutputSystem {

    private OutputStream stream;

    /**
     * Creates ConsoleOutput object
     */
    public OutputWriter(OutputStream stream) {
        this.stream = stream;
    }

    /**
     * Send OK message to the output
     *
     */
    @Override
    public void sendOk() throws OutputException {
        try {
            stream.write((OK + "\n").getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            throw new OutputException(e);
        }
    }

    /**
     * Send ERROR message to the output
     *
     */
    @Override
    public void sendError() throws OutputException {
        try {
            stream.write((ERROR + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new OutputException(e);
        }
    }

    /**
     * Send message to the output
     *
     * @param message information message
     */
    @Override
    public void sendMessage(String message) throws OutputException {
        try {
            stream.write((message + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new OutputException(e);
        }
    }

    @Override
    public void close() throws OutputException {
        try {
            stream.close();
        } catch (IOException e) {
            throw new OutputException(e);
        }
    }
}
