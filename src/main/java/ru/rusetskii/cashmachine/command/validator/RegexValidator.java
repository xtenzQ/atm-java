package ru.rusetskii.cashmachine.command.validator;

/**
 * Validates given parameter with regex
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class RegexValidator implements Validator {

    private String regex;

    /**
     * Creates the Regex Validator
     *
     * @param regex regex statement
     */
    public RegexValidator(String regex) {
        this.regex = regex;
    }

    /**
     * Checks validity of the parameter to its regex
     *
     * @param param command parameter
     * @return <code>true</code> is parameter is valid; <code>false</code> otherwise.
     */
    @Override
    public boolean isValid(String param) {
        return param.matches(regex);
    }
}
