package ru.rusetskii.output;

public class FileOutput implements OutputSystem {

    /**
     * Send OK message to the output
     *
     * @throws OutputException if an exception occurred
     */
    @Override
    public void ok() throws OutputException {

    }

    /**
     * Send ERROR message to the output
     *
     * @throws OutputException if an exception occurred
     */
    @Override
    public void error() throws OutputException {

    }

    /**
     * Send message to the output
     *
     * @param message information message
     * @throws OutputException if an exception occurred
     */
    @Override
    public void message(String message) throws OutputException {

    }
}
