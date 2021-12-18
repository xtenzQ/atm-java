package ru.rusetskii.cashmachine.command.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexValidatorTest {

    @Test
    public void testIsValidSuccess() {
        RegexValidator regexValidator = new RegexValidator("[A-Z]{3}");
        assertTrue(regexValidator.isValid("USD"));
    }

    @Test
    public void testIsValidFail() {
        RegexValidator regexValidator = new RegexValidator("[A-Z]{3}");
        assertFalse(regexValidator.isValid("eur"));
    }
}