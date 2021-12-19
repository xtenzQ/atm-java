package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;
import ru.rusetskii.cashmachine.command.validator.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * Command is the abstract base class for all commands which allows application to execute different business logic
 * A Command object encapsulates the state information needed for the various command operations.
 * This state information includes:
 * <ul>
 *     <li>The Parameters;</li>
 *     <li>The Validators used to validate the parameters;</li>
 * </ul>
 * <p>
 * Some important points to consider are that parameters can be only initialized after object creation since commands
 * are predefined on initialization.
 */
public abstract class Command {
    private String operation;
    private CommandArguments params;
    private List<Validator> validators;

    public String getOperation() {
        return operation;
    }

    /**
     * Creates empty command
     */
    public Command() {
    }

    /**
     *
     * @param operation
     */
    public Command(String operation) {
        this.operation = operation;
    }

    /**
     * Initializes command with the specified list of validators.
     *
     * @param validators the list of validators
     * @see Validator
     */
    public Command(String operation, Validator... validators) {
        this.operation = operation;
        this.validators = Arrays.asList(validators);
    }

    /**
     * Returns the parameters of the command. Available only for its children.
     *
     * @return commands parameters
     */
    protected CommandArguments getParams() {
        return params;
    }

    /**
     * Sets parameters of the command
     *
     * @param params command parameters
     */
    public void setParams(CommandArguments params) throws InvalidCommandException {
        if (validate(params)) {
            this.params = params;
        }
        else {
            throw new InvalidCommandException("Invalid arguments!");
        }
    }

    /**
     * Implements business logic of the command
     * Methods get {@link CashMachine} as an argument, so it allows transferring management to the main program
     *
     * @param cashMachine business logic methods execution
     * @throws CommandExecutionException exception thrown during command execution
     * @see CashMachine
     */
    public abstract void execute(CashMachine cashMachine) throws CommandExecutionException;

    /**
     * Returns the state of validation
     * <p>
     * Method checks the presence of validations and their size. If the number of parameters mismatches the number
     * of validators, return <code>false</code>.
     *
     * Also, mind the order of the validators and the parameters.
     *
     * @throws InvalidCommandException if parameters number are not equal to the number of validators
     * @return <code>true</code> if validation is successful or validators are empty; <code>false</code> otherwise.
     * @see Validator
     */
    private boolean validate(CommandArguments params) {
        if (validators == null) return true;
        if (params.toListOfStrings().size() != validators.size()) return false;
        for (int i = 0; i < validators.size(); i++) {
            if (!validators.get(i).isValid(params.toListOfStrings().get(i))) {
                return false;
            }
        }
        return true;
    }
}
