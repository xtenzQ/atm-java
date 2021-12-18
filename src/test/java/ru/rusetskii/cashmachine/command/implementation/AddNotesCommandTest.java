package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;

import java.util.List;

import static org.mockito.Mockito.*;

public class AddNotesCommandTest {

    @Test
    public void testExecute() throws Exception {
        @SuppressWarnings("unchecked")
        List<String> parameters = mock(List.class);
        when(parameters.get(0)).thenReturn("USD");
        when(parameters.get(1)).thenReturn("100");
        when(parameters.get(2)).thenReturn("10");

        CashMachine atm = mock(CashMachine.class);

        AddNotesCommand command = new AddNotesCommand();
        command.setParams(parameters);
        command.execute(atm);

        verify(atm).deposit(eq("USD"), eq(100), eq(10));
    }
}