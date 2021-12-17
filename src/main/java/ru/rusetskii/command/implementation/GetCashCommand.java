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
public class GetCashCommand extends Command {

    /**
     *
     * @param validators
     */
    public GetCashCommand(Validator...validators) {
        super(validators);
    }

    /**
     *
     * @param cashMachine business logic methods execution
     * @throws CommandExecutionException
     */
    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            String currency = getParams().get(0);
            int amount = Integer.parseInt(getParams().get(1));
            cashMachine.getCash(currency, amount);
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
