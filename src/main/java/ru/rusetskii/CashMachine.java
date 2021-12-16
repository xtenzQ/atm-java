package ru.rusetskii;

import ru.rusetskii.atm.DenominationStorage;
import ru.rusetskii.atm.CashStorage;
import ru.rusetskii.command.*;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.exception.InvalidCommandException;
import ru.rusetskii.command.implementation.AddNotesCommand;
import ru.rusetskii.command.implementation.GetCashCommand;
import ru.rusetskii.command.implementation.PrintCashCommand;
import ru.rusetskii.command.validator.AmountValidator;
import ru.rusetskii.command.validator.DenominationValidator;
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
import java.util.logging.Logger;

/**
 * Cash Machine basic emulator
 */
public class CashMachine {
    private final static Logger LOGGER = Logger.getLogger();
    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;
    private final Map<String, Command> commands;
    private final CashStorage cashStorage;

    /**
     * CashMachine constructor
     */
    public CashMachine() {
        // replace with another I/O system
        this.inputSystem = new ConsoleInput();
        this.outputSystem = new ConsoleOutput();

        this.commands = new HashMap<>();
        this.cashStorage = new CashStorage();

        commandsInit();
    }

    /**
     * Initializes commands with their validators
     */
    private void commandsInit() {
        Validator currencyValidator = new CurrencyValidator();
        Validator denominationValidator = new DenominationValidator();
        Validator amountValidator = new AmountValidator();

        Command AddNotesCmd = new AddNotesCommand(List.of(currencyValidator, denominationValidator, amountValidator));
        Command GetCashCmd = new GetCashCommand(List.of(currencyValidator, amountValidator));
        Command PrintCashCmd = new PrintCashCommand();

        this.commands.put("+", AddNotesCmd);
        this.commands.put("-", GetCashCmd);
        this.commands.put("?", PrintCashCmd);
    }

    /**
     * Main loop of Cash Machine execution
     *
     * @throws OutputException           if occurred output exception
     * @throws CommandExecutionException if exception is thrown during command execution
     */
    public void run() throws OutputException, CommandExecutionException {
        while (true) {
            String inputLine = inputSystem.input();
            CommandReader reader = new CommandReader(inputLine);
            try {
                List<String> params = reader.readCommand();
                // gets the command by its operation
                Command command = commands.get(params.get(0));
                // retrieve the params
                command.setParams(params.subList(1, params.size()));
                if (command.validate()) {
                    command.execute(this);
                } else {
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
     * @param currency     currency code
     * @param denomination banknote value
     * @param amount       number of banknotes
     * @throws OutputException if exception occurred during writing output stream
     */
    public void addNotes(String currency, int denomination, int amount) throws OutputException {
        cashStorage.addNotes(currency, denomination, amount);
        outputSystem.sendOk();
    }

    /**
     * Get cash from the cash storage
     *
     * @param currency currency code
     * @param amount   number of banknotes
     * @throws OutputException if exception occurred during writing output stream
     */
    public void getCash(String currency, int amount) throws OutputException {
        DenominationStorage cashAfterWithdrawal = cashStorage.getCash(currency, amount);
        if (cashAfterWithdrawal != null) {
            for (int denomination : cashAfterWithdrawal.getDenominations()) {
                int numberOfBanknotes = cashAfterWithdrawal.getAmountByDenomination(denomination);
                // display leftovers
                outputSystem.sendMessage(String.format("%s %d %d", currency, denomination, numberOfBanknotes));
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
        for (String currency : cashStorage.getCurrencies()) {
            DenominationStorage cashOfCertainCurrency = cashStorage.getCash(currency);
            for (int denomination : cashOfCertainCurrency.getDenominations()) {
                int numberOfBanknotes = cashOfCertainCurrency.getAmountByDenomination(denomination);
                outputSystem.sendMessage(String.format("%s %d %d", currency, denomination, numberOfBanknotes));
            }
        }
        outputSystem.sendOk();
    }
}
