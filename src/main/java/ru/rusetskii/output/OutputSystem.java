package ru.rusetskii.output;

public interface OutputSystem {

    /**
     * Send OK message to the output
     *
     * @throws OutputException if an exception occurred
     */
    void ok() throws OutputException;

    /**
     * Send ERROR message to the output
     *
     * @throws OutputException if an exception occurred
     */
    void error() throws OutputException;

    /**
     * Send message to the output
     *
     * @param message information message
     * @throws OutputException if an exception occurred
     */
    void message(String message) throws OutputException;
}
