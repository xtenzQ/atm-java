package ru.rusetskii.command.implementation;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.Command;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;
import ru.rusetskii.output.OutputException;

import java.util.List;

/**
 *
 */
public class AddNotesCommand extends Command {

    public AddNotesCommand(List<Validator> validators) {
        super(validators);
    }

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
