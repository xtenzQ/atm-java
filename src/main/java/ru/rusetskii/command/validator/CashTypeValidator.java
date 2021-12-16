package ru.rusetskii.command.validator;

import java.util.Arrays;
import java.util.List;

public class CashTypeValidator implements Validator {

    public static final List<String> validValues = Arrays.asList(
            "1","5","10","50","100","500","1000","5000"
    );

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    @Override
    public boolean isValid(String param) {
        return validValues.contains(param);
    }
}
