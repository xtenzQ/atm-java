package ru.rusetskii.command.implementation;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.Command;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.output.OutputException;

/**
 *
 */
public class PrintCashCommand extends Command {

    public PrintCashCommand() { }

    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            cashMachine.printCash();
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
