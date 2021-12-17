package ru.rusetskii.cashmachine.command.validator;

import java.util.Arrays;
import java.util.List;

/**
 * Validates if the specified params is in the list
 */
public class SubsetValidator implements Validator {

    private List<String> values;

    /**
     * Creates the subset validator
     *
     * @param values the varargs transformed into the list
     */
    public SubsetValidator(String...values) {
        this.values = Arrays.asList(values);
    }

    /**
     * Validates if the specified params is in the given list
     *
     * @param param command parameter
     * @return <code>true</code> is parameter is in the list; <code>false</code> otherwise.
     */
    @Override
    public boolean isValid(String param) {
        return values.contains(param);
    }
}
