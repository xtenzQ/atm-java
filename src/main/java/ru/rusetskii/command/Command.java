package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * Command description class
 */
public abstract class Command {
    // params can only be initialized after object creation, so we only initialize validators in the constructor
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
    public Command(Validator...validators) {
        this.validators = Arrays.asList(validators);
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

    /**
     * Sets parameters of the command
     *
     * @param params command parameters
     */
    public void setParams(List<String> params) {
        this.params = params;
    }

    /**
     * Command's business logic
     *
     * @param cashMachine business logic methods execution
     * @throws CommandExecutionException exception thrown during command execution
     */
    public abstract void execute(CashMachine cashMachine) throws CommandExecutionException;

    /**
     * Default implementation of the common validation mechanism
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
}
