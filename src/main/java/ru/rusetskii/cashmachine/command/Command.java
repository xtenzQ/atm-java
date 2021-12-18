package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.exception.ParametersMismatchException;
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
    private List<String> params;
    private List<Validator> validators;

    /**
     * Creates empty command
     */
    public Command() {
    }

    /**
     * Initializes command with the specified list of validators.
     *
     * @param validators the list of validators
     * @see Validator
     */
    public Command(Validator... validators) {
        this.validators = Arrays.asList(validators);
    }

    /**
     * Returns the parameters of the command. Available only for its children.
     *
     * @return commands parameters
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
     * @throws ParametersMismatchException if parameters number are not equal to the number of validators
     * @return <code>true</code> if validation is successful or validators are empty; <code>false</code> otherwise.
     * @see Validator
     */
    public boolean validate() throws ParametersMismatchException {
        if (validators.size() == 0) return true;
        if (params.size() == validators.size()) {
            for (int i = 0; i < validators.size(); i++) {
                if (!validators.get(i).isValid(params.get(i))) {
                    return false;
                }
            }
            return true;
        }
        else {
            throw new ParametersMismatchException();
        }
    }
}
