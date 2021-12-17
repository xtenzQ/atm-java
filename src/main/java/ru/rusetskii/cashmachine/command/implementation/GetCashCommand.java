package ru.rusetskii.cashmachine.command.implementation;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.validator.Validator;
import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Delegates cash withdrawal to the Cash Machine
 */
public class GetCashCommand extends Command {

    /**
     * Creates withdrawal command
     *
     * @param validators validators
     */
    public GetCashCommand(Validator...validators) {
        super(validators);
    }

    /**
     * Calls business logic method to withdraw money from the Cash Machine
     * @param cashMachine provides Cash Machine to call business logic methods
     * @throws CommandExecutionException exception thrown during command execution
     * @see CashMachine
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
