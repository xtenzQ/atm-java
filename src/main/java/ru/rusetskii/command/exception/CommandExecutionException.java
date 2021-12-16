package ru.rusetskii.command.exception;

/**
 * Exception thrown during command execution
 */
public class CommandExecutionException extends Exception {
    public CommandExecutionException(Throwable e) {
        super(e);
    }
}
