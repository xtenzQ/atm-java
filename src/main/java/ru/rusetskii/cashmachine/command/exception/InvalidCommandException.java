package ru.rusetskii.cashmachine.command.exception;

/**
 * Exception is thrown if command is not found
 */
public class InvalidCommandException extends Exception {
    /**
     * Creates new Command Execution exception simply by inheriting args from its parent
     * @param message information message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
