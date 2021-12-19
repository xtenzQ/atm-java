package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GetCashCommandTest {

    @Test
    public void testExecute() throws Exception {
        List<String> parameters = Arrays.asList("USD", "1000");

        CashMachine atm = mock(CashMachine.class);

        GetCashCommand command = new GetCashCommand();
        command.setArgs(parameters);
        command.execute(atm);

        verify(atm).withdraw(eq("USD"), eq(1000));
    }

}