package ru.rusetskii.cashmachine.input;

import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Defines the input system methods
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public interface InputSystem {

    /**
     * Read command from the input
     *
     * @return command
     */
    String input();

    /**
     * Closes read stream
     *
     * @throws OutputException if an error occured
     */
    void close() throws OutputException;

    /**
     * Checks next command availability
     *
     * @return <code>true</code> if exists; <code>false</code> otherwise.
     */
    boolean inputAvailable();
}
