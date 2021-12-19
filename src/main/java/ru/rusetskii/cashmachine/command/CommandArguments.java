package ru.rusetskii.cashmachine.command;

import java.util.List;

/**
 *
 */
public class CommandArguments {

    private String operation;
    private List<String> params;

    /**
     * Creates command arguments
     * @param operation
     * @param params
     */
    public CommandArguments(String operation, List<String> params) {
        this.operation = operation;
        this.params = params;
    }

    /**
     * Returns command operation code
     * @return operation code
     */
    public String getOperation() {
        return operation;
    }

    /**
     *
     * @return
     */
    public List<String> toListOfStrings() {
        return params;
    }

}
