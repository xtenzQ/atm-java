package ru.rusetskii.cashmachine.command.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubsetValidatorTest {

    @Test
    public void testIsValidSuccess() {
        SubsetValidator subsetValidator = new SubsetValidator("100", "500", "1000");
        assertTrue(subsetValidator.isValid("100"));
    }

    @Test
    public void testIsValidFail() {
        SubsetValidator subsetValidator = new SubsetValidator("100", "500", "1000");
        assertFalse(subsetValidator.isValid("5000"));
    }
}