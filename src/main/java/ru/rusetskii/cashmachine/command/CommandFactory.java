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
    public Command createCommand(CommandArguments commandArguments) throws InvalidCommandException {
        Command command = availableCommands.stream().
                filter(cmd -> cmd.getOperation().equals(commandArguments.getOperation())).findFirst().orElseThrow();
        command.setParams(commandArguments);
        return command;
    }

}
