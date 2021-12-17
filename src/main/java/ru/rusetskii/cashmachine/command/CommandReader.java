package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;

import java.util.List;

/**
 * Reads the string and returns the list of parameters
 */
public class CommandReader {

    private String commandString;

    /**
     * Default constructor
     *
     * @param commandString raw string
     */
    public CommandReader(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Transforms string into parameters list by space symbol
     *
     * @return list of parameters
     * @throws InvalidCommandException thrown if commands are not found or invalid
     */
    public List<String> readCommand() throws InvalidCommandException {
        if (!commandString.trim().isEmpty()) {
            return List.of(commandString.split("\\s+"));
        }
        else {
            throw new InvalidCommandException("Command is empty!");
        }
    }
}
