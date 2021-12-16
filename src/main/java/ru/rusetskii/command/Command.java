package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;

import java.util.List;

/**
 * Command description class
 */
public abstract class Command {
    // params can only be initialized after object creation
    private List<String> params;
    private List<Validator> validators;

    /**
     * Default constructor
     */
    public Command() {
    }

    /**
     * Initialize command with validators
     *
     * @param validators initialize the list of validators
     */
    public Command(List<Validator> validators) {
        this.validators = validators;
    }

    /**
     * Command's business logic execution
     *
     * @param cashMachine business logic methods execution
     * @throws CommandExecutionException exception thrown during command execution
     */
    public abstract void execute(CashMachine cashMachine) throws CommandExecutionException;

    /**
     * Implements the common validation mechanism for all commands
     *
     * @return if validation is successful
     */
    public boolean validate() {
        // no validators means no checks, just return
        if (validators == null) return true;
        // validation fails on validation/params mismatch
        if (params.size() == validators.size()) {
            // mind the order
            for (int i = 0; i < validators.size(); i++) {
                if (!validators.get(i).isValid(params.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Sets parameters of the command
     *
     * @param params parameteres
     */
    public void setParams(List<String> params) {
        this.params = params;
    }

    /**
     * Retrieves the params of the command
     * Available only for its children
     *
     * @return parameters of the command
     */
    protected List<String> getParams() {
        return params;
    }
}
