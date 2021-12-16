package ru.rusetskii.command;

import ru.rusetskii.CashMachine;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private List<String> params = new ArrayList<>();

    public void setParams(List<String> params) {
        this.params = params;
    }

    public List<String> getParams() {
        return params;
    }

    public abstract void execute(CashMachine cashMachine) throws CommandExecutionException;
}
