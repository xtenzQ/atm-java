package ru.rusetskii.command;

import ru.rusetskii.command.exception.InvalidCommandException;

import java.util.List;

/**
 * Reads the string and returns the list of parameters
 */
public class CommandReader {

    private String commandString;

    /**
     * Default constructor
     *
     * @param commandString
     */
    public CommandReader(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Transforms commandString string into parameters list by space
     * @return
     * @throws InvalidCommandException
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
