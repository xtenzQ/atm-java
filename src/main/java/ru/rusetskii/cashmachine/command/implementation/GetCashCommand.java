package ru.rusetskii.cashmachine.command.implementation;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.validator.RegexValidator;
import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Delegates cash withdrawal to the Cash Machine
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class GetCashCommand extends Command {

    /**
     * Creates withdrawal command
     */
    public GetCashCommand() {
        super("-", new RegexValidator("[A-Z]{3}"),
                new RegexValidator("[1-9][0-9]*"));
    }

    /**
     * Calls business logic method to withdraw money from the Cash Machine
     *
     * @param cashMachine provides Cash Machine to call business logic methods
     * @throws CommandExecutionException exception thrown during command execution
     * @see CashMachine
     */
    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        try {
            String currency = getArgs().get(0);
            int amount = Integer.parseInt(getArgs().get(1));
            cashMachine.withdraw(currency, amount);
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
