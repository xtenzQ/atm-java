package ru.rusetskii.command.implementation;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.Command;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;
import ru.rusetskii.output.OutputException;

import java.util.List;

public class GetCashCommand extends Command {

    public GetCashCommand(List<Validator> validators) {
        super(validators);
    }

    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            // currency, number
            cashMachine.getCash(getParams().get(0), Integer.parseInt(getParams().get(1)));
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
