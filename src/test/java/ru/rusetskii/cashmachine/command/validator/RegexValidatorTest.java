package ru.rusetskii.cashmachine.command.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexValidatorTest {

    @Test
    public void testSuccessfulValidation() {
        RegexValidator validator = new RegexValidator("[A-Z]{3}");
        assertTrue(validator.isValid("USD"));
        assertTrue(validator.isValid("RUR"));
    }

    @Test
    public void testFailedValidation() {
        RegexValidator validator = new RegexValidator("[A-Z]{3}");
        assertFalse(validator.isValid("rur1"));
        assertFalse(validator.isValid("us"));
    }
}