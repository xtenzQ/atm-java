package ru.rusetskii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rusetskii.cash_operation.CashStorage;
import ru.rusetskii.cash_operation.DenominationStorage;
import ru.rusetskii.command.Command;
import ru.rusetskii.command.CommandReader;
import ru.rusetskii.command.exception.CommandExecutionException;
import ru.rusetskii.command.exception.InvalidCommandException;
import ru.rusetskii.command.implementation.AddNotesCommand;
import ru.rusetskii.command.implementation.GetCashCommand;
import ru.rusetskii.command.implementation.PrintCashCommand;
import ru.rusetskii.command.validator.RegexValidator;
import ru.rusetskii.command.validator.DenominationValidator;
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
    private static Logger logger = LogManager.getLogger(CashMachine.class.getName());

    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;
    // <operation, command>
    private final Map<String, Command> commands;
    private final CashStorage cashStorage;

    /**
     * CashMachine constructor
     */
    public CashMachine() {
        // replace with any other I/O system
        this.inputSystem = new ConsoleInput();
        this.outputSystem = new ConsoleOutput();

        this.commands = new HashMap<>();
        this.cashStorage = new CashStorage();
        configure();
    }

    /**
     * Initializes commands with the corresponding validators
     */
    private void configure() {
        Validator currencyValidator = new RegexValidator("[A-Z]{3}");
        Validator amountValidator = new RegexValidator("[1-9][0-9]*");
        Validator denominationValidator = new DenominationValidator("1","5","10","50","100","500","1000","5000");

        Command AddNotesCmd = new AddNotesCommand(currencyValidator, denominationValidator, amountValidator);
        Command GetCashCmd = new GetCashCommand(currencyValidator, amountValidator);
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
            logger.info("Input command : " + inputLine);
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
                    logger.error("Error on validation");
                }
            } catch (InvalidCommandException e) {
                outputSystem.sendError();
                logger.error("Error while executing command : " + e);
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
        logger.info("Cash deposit successful");
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
                int currentAmount = cashAfterWithdrawal.getAmountByDenomination(denomination);
                // display leftovers
                outputSystem.sendMessage(String.format("%s %d %d", currency, denomination, currentAmount));
                logger.info(String.format("Successful cash withdrawal : %s %d %d", currency, denomination, currentAmount));
            }
            outputSystem.sendOk();
        } else {
            outputSystem.sendError();
            logger.error("Error occurred while withdrawing cash");
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
                int amount = cashOfCertainCurrency.getAmountByDenomination(denomination);
                outputSystem.sendMessage(String.format("%s %d %d", currency, denomination, amount));
                logger.info(String.format("%s %d %d", currency, denomination, amount));
            }
        }
        outputSystem.sendOk();
        logger.info("Balance print successful");
    }
}
