package ru.rusetskii.cashmachine.command;

import ru.rusetskii.cashmachine.CashMachine;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command is the abstract base class for all commands which allows application to execute different business logic
 * A Command object encapsulates the state information needed for the various command operations.
 * This state information includes:
 * <ul>
 *     <li>The Operation code;</li>
 *     <li>The Parameters;</li>
 *     <li>The Validators used to validate the parameters;</li>
 * </ul>
 * <p>
 * Some important points to consider are that parameters can be only initialized after object creation since commands
 * are predefined on initialization.
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public abstract class Command {
    private String operation;
    private List<String> args;
    private List<Validator> validators;

    /**
     * Default constructor
     */
    public Command() {

    }

    /**
     * Builds Command with the specified operation
     * @param operation operation code
     */
    public Command(String operation) {
        this.operation = operation;
        this.args = new ArrayList<>();
    }

    /**
     * Initializes command with the specified operation code and list of validators.
     *
     * @param operation operation code
     * @param validators the list of validators
     * @see Validator
     */
    public Command(String operation, Validator... validators) {
        this.operation = operation;
        this.validators = Arrays.asList(validators);
    }

    /**
     * Returns operation code
     *
     * @return operation code
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Returns the parameters of the command. Available only for its children.
     *
     * @return commands parameters
     */
    protected List<String> getArgs() {
        return args;
    }

    /**
     * Sets parameters of the command
     *
     * @param args command parameters
     */
    public void setArgs(List<String> args) {
        this.args = args;
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
     * @return <code>true</code> if validation is successful or validators are empty; <code>false</code> otherwise.
     * @see Validator
     */
    public boolean validate() {
        if (validators == null || args == null) return true;
        if (args.size() != validators.size()) return false;
        for (int i = 0; i < args.size(); i++) {
            if (!validators.get(i).isValid(args.get(i))) {
                return false;
            }
        }
        return true;
    }
}
