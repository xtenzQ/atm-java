package ru.rusetskii.command;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private List<String> params;

    public CommandParser(String command) throws InvalidCommandException {
        if (!command.trim().isEmpty()) {
            params = Arrays.asList(command.split(" "));
        }
        else {
            throw new InvalidCommandException("Invalid command!");
        }
    }

    public String getName() {
        return params.get(0);
    }

    public List<String> getParams() {
        return params.subList(1, params.size());
    }
}
