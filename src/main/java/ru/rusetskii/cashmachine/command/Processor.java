package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;

import java.util.List;

/**
 * Takes input parameters, creates command and executes it
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class Processor {
    private final CommandFactory commandFactory;
    private final CashMachine cashMachine;

    /**
     * Creates new command factory
     *
     * @param cashMachine cashMachine object
     */
    public Processor(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
        commandFactory = new CommandFactory();
    }

    /**
     * Creates new command based on its arguments
     *
     * @param args command arguments
     * @throws CommandExecutionException if exception occurred during command execution
     * @throws InvalidCommandException   on validation fail
     */
    public void process(String args) throws CommandExecutionException, InvalidCommandException {
        List<String> arguments = parseArguments(args);
        Command command = commandFactory.createCommand(arguments);
        if (command.validate()) {
            command.execute(cashMachine);
        } else {
            throw new InvalidCommandException("Invalid command!");
        }
    }

    /**
     * @param args
     * @return
     * @throws InvalidCommandException
     */
    private List<String> parseArguments(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            List<String> listOfArgs = List.of(args.split("\\s+"));
            return listOfArgs;
        } else {
            throw new InvalidCommandException("Command is empty!");
        }
    }
}
