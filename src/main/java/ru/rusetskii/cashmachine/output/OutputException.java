package ru.rusetskii.cashmachine.output;

import java.io.IOException;

/**
 * Exception thrown in the OutputSystem implementations
 */
public class OutputException extends IOException {
    /**
     * Creates Output Exception and inherits its argument to the parent
     *
     * @param e Exception message
     */
    public OutputException(Throwable e) {
        super(e);
    }
}