package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;
import ru.rusetskii.cashmachine.command.implementation.AddNotesCommand;
import ru.rusetskii.cashmachine.command.implementation.GetCashCommand;
import ru.rusetskii.cashmachine.command.implementation.PrintCashCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all available commands and produces commands based on their arguments
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class CommandFactory {
    private final List<Command> availableCommands = new ArrayList<>();

    /**
     * Initializes commands
     */
    public CommandFactory() {
        availableCommands.add(new AddNotesCommand());
        availableCommands.add(new GetCashCommand());
        availableCommands.add(new PrintCashCommand());
    }

    /**
     * Returns command based on its operation code
     *
     * @param commandArguments command arguments (command code, parameters)
     * @return command from command list
     * @throws InvalidCommandException if arguments are invalid
     */
    public Command createCommand(List<String> commandArguments) throws InvalidCommandException {
        Command command = availableCommands.stream().
                filter(cmd -> cmd.getOperation().equals(getOperation(commandArguments))).findFirst().orElseThrow();
        command.setArgs(getArgs(commandArguments));
        return command;
    }

    /**
     * Returns operation code
     *
     * @param args list of arguments
     * @return operation code
     */
    public String getOperation(List<String> args) {
        return args.get(0);
    }

    /**
     * Returns list of arguments
     *
     * @param args full list of arguments
     * @return list without operation code
     */
    public List<String> getArgs(List<String> args) {
        return args.subList(1, args.size());
    }

}
