package ru.rusetskii.command.validator;

public class CashTypeValidator implements Validator {

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    @Override
    public boolean isValid(String param) {
        return false;
    }
}
