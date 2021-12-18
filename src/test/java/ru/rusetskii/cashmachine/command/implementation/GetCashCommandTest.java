package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GetCashCommandTest {

    @Test
    public void testExecute() throws Exception {
        @SuppressWarnings("unchecked")
        List<String> parameters = mock(List.class);
        when(parameters.get(0)).thenReturn("USD");
        when(parameters.get(1)).thenReturn("1000");

        CashMachine atm = mock(CashMachine.class);

        GetCashCommand command = new GetCashCommand();
        command.setParams(parameters);
        command.execute(atm);

        verify(atm).withdraw(eq("USD"), eq(1000));
    }

}