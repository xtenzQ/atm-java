package ru.rusetskii.output;

import java.io.IOException;

/**
 * Exception thrown in the OutputSystem implementations
 */
public class OutputException extends IOException {
    public OutputException(Throwable e) {
        super (e);
    }
}