package ru.rusetskii.cashmachine.command.validator;

/**
 * Validates command parameter
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
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
