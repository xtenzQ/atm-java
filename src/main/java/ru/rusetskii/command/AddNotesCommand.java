package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.output.OutputException;

/**
 *
 */
public class AddNotesCommand extends Command {

    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        String currency = getParams().get(0);
        int value = Integer.parseInt(getParams().get(1));
        int number = Integer.parseInt(getParams().get(2));

        try {
            cashMachine.addNotes(currency, value, number);
        }
        catch (OutputException exception) {
            throw new CommandExecutionException(exception);
        }
    }
}
