package ru.rusetskii.command.implementation;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.Command;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;
import ru.rusetskii.output.OutputException;

import java.util.List;

/**
 * Adds banknotes to the cash storage of the cash machine
 */
public class AddNotesCommand extends Command {

    /**
     * Default constructor
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
