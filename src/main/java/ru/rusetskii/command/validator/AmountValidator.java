package ru.rusetskii.command.validator;

public class AmountValidator implements Validator {

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    @Override
    public boolean isValid(String param) {
        return param.matches("[1-9][0-9]*");
    }
}
