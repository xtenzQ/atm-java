package ru.rusetskii.cashmachine.command.exception;

/**
 * Exception is thrown if parameters and validators number mismatch
 */
public class ParametersMismatchException extends Exception {
    /**
     * Creates new Parameters Mismatch exception and transfer the message
     */
    public ParametersMismatchException() {
        super("Parameters and Validators number mismatch!");
    }
}
