package ru.rusetskii.command.validator;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of
 */
public class DenominationValidator implements Validator {

    private List<String> values;

    /**
     *
     * @param values
     */
    public DenominationValidator(String...values) {
        this.values = Arrays.asList(values);
    }

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    @Override
    public boolean isValid(String param) {
        return values.contains(param);
    }
}
