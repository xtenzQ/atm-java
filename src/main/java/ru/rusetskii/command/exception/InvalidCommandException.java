package ru.rusetskii.command.exception;

/**
 * Exception thrown if command is not found
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
