package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.CommandArguments;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddNotesCommandTest {

    @Test
    public void testExecute() throws Exception {
        CommandArguments commandArguments = new CommandArguments("+", Arrays.asList("USD", "100", "10"));
        CashMachine cashMachine = mock(CashMachine.class);
        AddNotesCommand command = new AddNotesCommand();
        command.setParams(commandArguments);
        command.execute(cashMachine);
        verify(cashMachine).deposit(eq("USD"), eq(100), eq(10));
    }
}