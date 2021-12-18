package ru.rusetskii.cashmachine.command;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.ParametersMismatchException;
import ru.rusetskii.cashmachine.command.validator.RegexValidator;
import ru.rusetskii.cashmachine.command.validator.SubsetValidator;
import ru.rusetskii.cashmachine.command.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandTest {

    private class SomeCommand extends Command {
        public SomeCommand() {

        }

        public SomeCommand(Validator...validators) {
            super(validators);
        }

        @Override
        public void execute(CashMachine cashMachine) {
        }
    }


    @Test
    public void testValidateEmptyValidators() throws ParametersMismatchException {
        SomeCommand command = new SomeCommand();
        assertTrue(command.validate());
    }

    @Test(expected = ParametersMismatchException.class)
    public void testValidateParamsMismatch() throws ParametersMismatchException {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "10"));
        Validator validator = new RegexValidator("[A-Z]{3}");
        SomeCommand command = new SomeCommand(validator);
        command.setParams(params);
        command.validate();
    }

    @Test
    public void testValidateSuccess() throws ParametersMismatchException {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "10"));
        Validator regexValidator = new RegexValidator("[A-Z]{3}");
        Validator subSetValidator = new SubsetValidator("10");
        SomeCommand command = new SomeCommand(regexValidator, subSetValidator);
        command.setParams(params);
        assertTrue(command.validate());
    }

    @Test
    public void testValidateFail() throws ParametersMismatchException {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "100"));
        Validator regexValidator = new RegexValidator("[A-Z]{3}");
        Validator subSetValidator = new SubsetValidator("10");
        SomeCommand command = new SomeCommand(regexValidator, subSetValidator);
        command.setParams(params);
        assertFalse(command.validate());
    }
}