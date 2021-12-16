package ru.rusetskii;

import ru.rusetskii.atm.Cash;
import ru.rusetskii.atm.CashStorage;
import ru.rusetskii.command.*;
import ru.rusetskii.input.InputSystem;
import ru.rusetskii.output.OutputException;
import ru.rusetskii.output.OutputSystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Cash Machine implementation
 */
public class CashMachine {
    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;

    // <code, command>
    private Map<String, Command> commandStorage;
    private CashStorage cashStorage;

    public CashMachine(InputSystem inputSystem, OutputSystem outputSystem) {

        // init io system
        this.inputSystem = inputSystem;
        this.outputSystem = outputSystem;

        // init command storage and cash storage
        this.commandStorage = new HashMap<>();
        this.cashStorage = new CashStorage();

        // init commands
        commandStorage.put("+", new AddNotesCommand());
        commandStorage.put("-", new GetCashCommand());
        commandStorage.put("?", new PrintCashCommand());
    }

    public void run() throws OutputException, CommandExecutionException {
        while (true) {
            try {
                CommandParser commandParser = new CommandParser(inputSystem.input());
                if (commandStorage.containsKey(commandParser.getName())) {
                    Command command = commandStorage.get(commandParser.getName());
                    command.setParams(commandParser.getParams());
                    command.execute(this);
                } else {
                    outputSystem.error();
                }
            } catch (InvalidCommandException e){
                outputSystem.error();
            }
        }
    }

    public void addNotes(String currency, int value, int number) throws OutputException {
        cashStorage.addNotes(currency, value, number);
        outputSystem.ok();
    }

    public void getCash(String currency, int amount) throws OutputException {
        Cash cash = cashStorage.getCash(currency, amount);
        if (cash != null) {
            for (int notesValue : cash.getValues()) {
                String message = "";
                message += notesValue + " ";
                message += cash.getNumber(notesValue) + "\n";

                outputSystem.message(message);
            }
            outputSystem.ok();
        } else {
            outputSystem.error();
        }
    }

    public void printCash() throws OutputException {
        for (String currencyCode : cashStorage.getValues()) {
            Cash notesHolder = cashStorage.getCash(currencyCode);

            for (int notesValue : notesHolder.getValues()) {
                String outputString = currencyCode + " ";
                outputString += notesValue + " ";
                outputString += notesHolder.getNumber(notesValue) + "\n";

                outputSystem.message(outputString);
            }
        }

        outputSystem.ok();
    }
}
