package ru.rusetskii.command.validator;

/**
 * Validates command parameter
 */
public interface Validator {

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    boolean isValid(String param);
}
