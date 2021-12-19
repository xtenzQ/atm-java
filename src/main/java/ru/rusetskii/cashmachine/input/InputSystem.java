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

    void close() throws OutputException;

    boolean inputAvailable();
}
