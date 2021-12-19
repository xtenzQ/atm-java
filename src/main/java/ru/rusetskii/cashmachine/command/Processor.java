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
    private CommandFactory commandFactory;
    private CashMachine cashMachine;

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
     * @throws InvalidCommandException on validation fail
     */
    public void process(String args) throws CommandExecutionException, InvalidCommandException {
        CommandArguments arguments = parseArguments(args);
        Command command = commandFactory.createCommand(arguments);
        command.execute(cashMachine);
    }

    /**
     *
     * @param args
     * @return
     * @throws InvalidCommandException
     */
    private CommandArguments parseArguments(String args) throws InvalidCommandException {
        if (!args.isEmpty()) {
            List<String> listOfArgs = List.of(args.split("\\s+"));
            return new CommandArguments(getOperationCode(listOfArgs), getArgs(listOfArgs));
        }
        else {
            throw new InvalidCommandException("Command is empty!");
        }
    }

    /**
     *
     * @param args
     * @return
     */
    private String getOperationCode(List<String> args) {
        return args.get(0);
    }

    /**
     *
     * @param args
     * @return
     */
    private List<String> getArgs(List<String> args) {
        return args.subList(1, args.size());
    }
}
