package ru.rusetskii.cashmachine.output;

import static ru.rusetskii.cashmachine.output.MessageConstants.*;

/**
 * Provides console output
 */
public class ConsoleOutput implements OutputSystem {

    /**
     * Creates ConsoleOutput object
     */
    public ConsoleOutput() {

    }

    /**
     * Send OK message to the output
     *
     */
    @Override
    public void sendOk() {
        System.out.println(OK);
    }

    /**
     * Send ERROR message to the output
     *
     */
    @Override
    public void sendError() {
        System.out.println(ERROR);
    }

    /**
     * Send message to the output
     *
     * @param message information message
     */
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
