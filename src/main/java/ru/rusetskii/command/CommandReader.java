package ru.rusetskii.command;

import ru.rusetskii.command.exception.InvalidCommandException;

import java.util.Arrays;
import java.util.List;

public class CommandReader {

    private String command;

    public CommandReader(String command) {
        this.command = command;
    }

    public List<String> readCommand() throws InvalidCommandException {
        if (!command.trim().isEmpty()) {
            return List.of(command.split("\\s+"));
        }
        else {
            throw new InvalidCommandException("Command is empty!");
        }
    }

}
