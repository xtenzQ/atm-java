package ru.rusetskii.output;

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
}
