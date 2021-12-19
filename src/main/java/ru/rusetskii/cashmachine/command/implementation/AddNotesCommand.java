package ru.rusetskii.cashmachine.command.implementation;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.validator.RegexValidator;
import ru.rusetskii.cashmachine.command.validator.SubsetValidator;
import ru.rusetskii.cashmachine.output.OutputException;

/**
 * Delegates cash deposit to the Cash Machine
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class AddNotesCommand extends Command {

    /**
     * Creates deposit command
     */
    public AddNotesCommand() {
        super("+", new RegexValidator("[A-Z]{3}"),
                new SubsetValidator("1","5","10","50","100","500","1000","5000"),
                new RegexValidator("[1-9][0-9]*"));
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
            String currency = getArgs().get(0);
            int denomination = Integer.parseInt(getArgs().get(1));
            int amount = Integer.parseInt(getArgs().get(2));
            cashMachine.deposit(currency, denomination, amount);
        }
        catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
