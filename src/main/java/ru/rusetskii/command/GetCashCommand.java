package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.output.OutputException;

public class GetCashCommand extends Command {

    @Override
    public void execute(CashMachine cashMachine) throws CommandExecutionException {
        String currency = getParams().get(0);
        int amount = Integer.parseInt(getParams().get(1));
        try {
            cashMachine.getCash(currency, amount);
        } catch (OutputException e) {
            throw new CommandExecutionException(e);
        }
    }
}
