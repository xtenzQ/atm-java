package ru.rusetskii.cashmachine.command.exception;

/**
 * Exception is thrown during command execution
 */
public class CommandExecutionException extends Exception {
    /**
     * Creates new Command Execution exception simply by inheriting args from its parent
     *
     * @param e Exception message
     */
    public CommandExecutionException(Throwable e) {
        super(e);
    }
}
