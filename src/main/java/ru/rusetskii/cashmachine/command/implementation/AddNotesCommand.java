package ru.rusetskii.cashmachine.command.implementation;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.validator.Validator;
import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Delegates cash deposit to the Cash Machine
 */
public class AddNotesCommand extends Command {

    /**
     * Creates deposit command
     *
     * @param validators validators
     */
    public AddNotesCommand(Validator...validators) {
        super(validators);
    }

    /**
     * Calls business logic method to deposit money into the Cash Machine
     *
     * @param cashMachine provides Cash Machine to call business logic methods
     * @throws CommandExecutionException exception thrown during command execution
     * @see CashMachine
     */
    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            String currency = getParams().get(0);
            int denomination = Integer.parseInt(getParams().get(1));
            int amount = Integer.parseInt(getParams().get(2));
            cashMachine.addNotes(currency, denomination, amount);
        }
        catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
