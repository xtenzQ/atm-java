package ru.rusetskii.cashmachine.output;

import java.io.IOException;

/**
 * Defines interface for output writing system
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public interface OutputSystem {

    /**
     * Send OK message to the output
     *
     * @throws OutputException if an exception occurred
     */
    void sendOk() throws OutputException;

    /**
     * Send ERROR message to the output
     *
     * @throws OutputException if an exception occurred
     */
    void sendError() throws OutputException;

    /**
     * Send message to the output
     *
     * @param message information message
     * @throws OutputException if an exception occurred
     */
    void sendMessage(String message) throws OutputException;

    /**
     * Closes output stream
     *
     * @throws OutputException if an exception occurred
     */
    void close() throws OutputException;
}
