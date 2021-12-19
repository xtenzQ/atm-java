package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.CommandArguments;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GetCashCommandTest {

    @Test
    public void testExecute() throws Exception {
        CommandArguments commandArguments = new CommandArguments("-", Arrays.asList("USD", "1000"));
        CashMachine cashMachine = mock(CashMachine.class);
        GetCashCommand command = new GetCashCommand();
        command.setParams(commandArguments);
        command.execute(cashMachine);
        verify(cashMachine).withdraw(eq("USD"), eq(1000));
    }

}