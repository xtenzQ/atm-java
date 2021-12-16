package ru.rusetskii;

import ru.rusetskii.atm.Cash;
import ru.rusetskii.atm.CashStorage;
import ru.rusetskii.command.*;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.exception.InvalidCommandException;
import ru.rusetskii.command.implementation.AddNotesCommand;
import ru.rusetskii.command.implementation.GetCashCommand;
import ru.rusetskii.command.implementation.PrintCashCommand;
import ru.rusetskii.command.validator.CashNumberValidator;
import ru.rusetskii.command.validator.CashTypeValidator;
import ru.rusetskii.command.validator.CurrencyValidator;
import ru.rusetskii.command.validator.Validator;
import ru.rusetskii.input.ConsoleInput;
import ru.rusetskii.input.InputSystem;
import ru.rusetskii.output.ConsoleOutput;
import ru.rusetskii.output.OutputException;
import ru.rusetskii.output.OutputSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cash Machine basic emulator
 */
public class CashMachine {
    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;

    private Map<String, Command> commands;
    private CashStorage cashStorage;

    /**
     * CashMachine constructor
     */
    public CashMachine() {
        this.inputSystem = new ConsoleInput();
        this.outputSystem = new ConsoleOutput();
        this.commands = new HashMap<>();
        this.cashStorage = new CashStorage();

        // init commands with validators
        Validator currencyValidator = new CurrencyValidator();
        Validator cashTypeValidator = new CashTypeValidator();
        Validator cashNumberValidator = new CashNumberValidator();

        Command AddNotesCmd = new AddNotesCommand(List.of(currencyValidator, cashTypeValidator, cashNumberValidator));
        Command GetCashCmd = new GetCashCommand(List.of(currencyValidator, cashNumberValidator));
        Command PrintCashCmd = new PrintCashCommand();

        this.commands.put("+", AddNotesCmd);
        this.commands.put("-", GetCashCmd);
        this.commands.put("?", PrintCashCmd);
    }

    /**
     * Main loop of Cash Machine execution
     *
     * @throws OutputException if occurred output exception
     * @throws CommandExecutionException if exception is thrown during command execution
     */
    public void run() throws OutputException, CommandExecutionException {
        while (true) {
            String inputLine = inputSystem.input();
            CommandReader reader = new CommandReader(inputLine);
            try {
                List<String> params = reader.readCommand();
                Command command = commands.get(params.get(0));
                command.setParams(params.subList(1, params.size()));
                if (command.validate()) {
                    command.execute(this);
                }
                else {
                    outputSystem.sendError();
                }
            } catch (InvalidCommandException e) {
                outputSystem.sendError();
            }
        }
    }

    /**
     * Add cash to the cash storage
     *
     * @param currency currency code
     * @param value    banknote value
     * @param number   number of banknotes
     * @throws OutputException if exception occurred during writing output stream
     */
    public void addNotes(String currency, int value, int number) throws OutputException {
        cashStorage.addNotes(currency, value, number);
        outputSystem.sendOk();
    }

    /**
     * Get cash from the cash storage
     *
     * @param currency currency code
     * @param number   number of banknotes
     * @throws OutputException if exception occurred during writing output stream
     */
    public void getCash(String currency, int number) throws OutputException {
        Cash cash = cashStorage.getCash(currency, number);
        if (cash != null) {
            for (int value : cash.getValues()) {
                outputSystem.sendMessage(String.format("%s %s %s", currency, value, cash.getNumber(value)));
            }
            outputSystem.sendOk();
        } else {
            outputSystem.sendError();
        }
    }

    /**
     * Print balance to output device
     *
     * @throws OutputException if exception occurred during writing output stream
     */
    public void printCash() throws OutputException {
        for (String currency : cashStorage.getValues()) {
            Cash cashByCurrency = cashStorage.getCash(currency);
            for (int value : cashByCurrency.getValues()) {
                outputSystem.sendMessage(String.format("%s %s %s", currency, value, cashByCurrency.getNumber(value)));
            }
        }
        outputSystem.sendOk();
    }
}
