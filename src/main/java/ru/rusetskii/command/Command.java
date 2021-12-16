package ru.rusetskii.command;

import ru.rusetskii.CashMachine;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.validator.Validator;

import java.util.List;

/**
 * Command description class
 */
public abstract class Command {
    private List<String> params;
    private List<Validator> validators;

    /**
     * Default constructor
     */
    public Command() { }

    /**
     *
     * @param validators initalize
     */
    public Command(List<Validator> validators) {
        this.validators = validators;
    }

    public abstract void execute(CashMachine cashMachine) throws CommandExecutionException;

    public boolean validate() {
        if (validators == null) return true;
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

    public void setParams(List<String> params) {
        this.params = params;
    }

    protected List<String> getParams() {
        return params;
    }
}
