package ru.rusetskii.cashmachine.command;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;
import ru.rusetskii.cashmachine.command.validator.RegexValidator;
import ru.rusetskii.cashmachine.command.validator.SubsetValidator;
import ru.rusetskii.cashmachine.command.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandTest {

    private class SomeCommand extends Command {
        public SomeCommand() {

        }

        public SomeCommand(String operation, Validator...validators) {
            super(operation, validators);
        }

        @Override
        public void execute(CashMachine cashMachine) {
        }
    }


    @Test
    public void testValidateEmptyValidators() throws Exception {
        SomeCommand command = new SomeCommand();
        assertTrue(command.validate());
    }

    @Test
    public void testValidateParamsMismatch() throws Exception {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "10"));
        Validator validator = new RegexValidator("[A-Z]{3}");
        SomeCommand command = new SomeCommand("Test", validator);
        command.setArgs(params);
        assertFalse(command.validate());
    }

    @Test
    public void testValidateSuccess() throws Exception {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "10"));
        Validator regexValidator = new RegexValidator("[A-Z]{3}");
        Validator subSetValidator = new SubsetValidator("10");
        SomeCommand command = new SomeCommand("Test",  regexValidator, subSetValidator);
        command.setArgs(params);
        assertTrue(command.validate());
    }

    @Test
    public void testValidateFail() throws Exception {
        List<String> params = new ArrayList<>(Arrays.asList("USD", "100"));
        Validator regexValidator = new RegexValidator("[A-Z]{3}");
        Validator subSetValidator = new SubsetValidator("10");
        SomeCommand command = new SomeCommand("Test", regexValidator, subSetValidator);
        command.setArgs(params);
        assertFalse(command.validate());
    }
}