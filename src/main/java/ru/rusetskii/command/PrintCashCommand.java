package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.output.OutputException;

public class PrintCashCommand extends Command {

    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            cashMachine.printCash();
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
