package ru.rusetskii.cashmachine.command;

import java.util.List;

/**
 * Represents command arguments
 *
 * Command argument consists of two common attributes:
 * <ul>
 *     <li>{@link #operation}- usually a symbol, but could a string, basically a primary key of an entity;</li>
 *     <li>{@link #params} - list of parameters (plural).</li>
 * </ul>
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
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
     * Returns list of arguments
     * @return arguments
     */
    public List<String> toListOfStrings() {
        return params;
    }

}
