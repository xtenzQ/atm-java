package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddNotesCommandTest {

    @Test
    public void testExecute() throws Exception {
        List<String> parameters = Arrays.asList("USD", "100", "10");

        CashMachine atm = mock(CashMachine.class);

        AddNotesCommand command = new AddNotesCommand();
        command.setArgs(parameters);
        command.execute(atm);

        verify(atm).deposit(eq("USD"), eq(100), eq(10));
    }
}