package ru.rusetskii.cashmachine.command.implementation;

import org.junit.Test;
import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrintCashCommandTest {

    @Test
    public void testExecute() throws Exception {
        PrintCashCommand command = new PrintCashCommand();
        CashMachine cashMachine = mock(CashMachine.class);
        command.execute(cashMachine);
        verify(cashMachine).print();
    }
}