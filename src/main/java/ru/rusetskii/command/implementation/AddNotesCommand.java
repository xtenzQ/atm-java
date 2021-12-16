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
    public AddNotesCommand(List<Validator> validators) {
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
            // currency, value, number
            cashMachine.addNotes(getParams().get(0), Integer.parseInt(getParams().get(1)), Integer.parseInt(getParams().get(2)));
        }
        catch (OutputException exception) {
            throw new CommandExecutionException(exception);
        }
    }
}
