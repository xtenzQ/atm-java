package ru.rusetskii.cashmachine.command.implementation;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Delegates balance print to the Cash Machine
 */
public class PrintCashCommand extends Command {

    /**
     * Creates print cash command
     */
    public PrintCashCommand() { super("?"); }

    /**
     * Calls business logic method to print balance
     * @param cashMachine business logic methods execution
     * @throws CommandExecutionException exception thrown during command execution
     */
    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            cashMachine.print();
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
