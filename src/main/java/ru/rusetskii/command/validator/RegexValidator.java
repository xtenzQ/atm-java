package ru.rusetskii.command.validator;

/**
 * Validates given parameter with regex
 */
public class RegexValidator implements Validator {

    private String regex;

    /**
     * Default constructor
     *
     * @param regex regex statement
     */
    public RegexValidator(String regex) {
        this.regex = regex;
    }

    /**
     * Checks validity of the parameter
     *
     * @param param command parameter
     * @return true is parameter is valid, false otherwise
     */
    @Override
    public boolean isValid(String param) {
        return param.matches(regex);
    }
}
