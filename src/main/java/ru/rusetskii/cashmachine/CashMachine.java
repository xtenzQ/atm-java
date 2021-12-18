package ru.rusetskii.cashmachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rusetskii.cashmachine.cash_operation.CashStorage;
import ru.rusetskii.cashmachine.cash_operation.DenominationStorage;
import ru.rusetskii.cashmachine.command.Command;
import ru.rusetskii.cashmachine.command.CommandReader;
import ru.rusetskii.cashmachine.command.exception.CommandExecutionException;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;
import ru.rusetskii.cashmachine.command.exception.ParametersMismatchException;
import ru.rusetskii.cashmachine.command.implementation.AddNotesCommand;
import ru.rusetskii.cashmachine.command.implementation.GetCashCommand;
import ru.rusetskii.cashmachine.command.implementation.PrintCashCommand;
import ru.rusetskii.cashmachine.input.ConsoleInput;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.ConsoleOutput;
import ru.rusetskii.cashmachine.output.OutputException;
import ru.rusetskii.cashmachine.output.OutputSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cash Machine basic emulator class.
 * <p>
 * The function of the simple cash machine includes:
 * <ul>
 *     <li>storage of I/O settings;</li>
 *     <li>commands initialization (including validators and parameters);</li>
 *     <li>cash storage initialization;</li>
 *     <li>basic user interface (such as console);</li>
 *     <li>business logic methods;</li>
 * </ul>
 *
 * Commands pattern implementation
 *
 */
public class CashMachine {
    private static Logger logger = LogManager.getLogger(CashMachine.class.getName());;

    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;
    private final Map<String, Command> commands;
    private final CashStorage cashStorage;

    /**
     * Creates Cash Machine object and does initialization
     */
    public CashMachine() {
        this.inputSystem = new ConsoleInput();
        this.outputSystem = new ConsoleOutput();

        this.commands = new HashMap<>();
        this.cashStorage = new CashStorage();
        configure();
    }

    /**
     * Initializes commands with the corresponding validators
     * <p>
     * Mind the order of the validators, so it should match the order of the command, otherwise
     * {@link ParametersMismatchException} will be thrown in the {@link #run} method.
     *
     * Also, after command initialization, add command to the command storage {@link #commands} with its correspoding
     * operator (<code>+</code>, <code>-</code>, etc.).
     */
    private void configure() {
        this.commands.put("+", new AddNotesCommand());
        this.commands.put("-", new GetCashCommand());
        this.commands.put("?", new PrintCashCommand());
    }

    /**
     * Main loop of Cash Machine execution.
     * <ol>
     *      <li>Reads command line from the {@link InputSystem}</li>
     *      <li>Parses the command via {@link CommandReader}</li>
     *      <li>Returns command by its operator from the {@link #commands} list</li>
     *      <li>Sets the parameters of the command</li>
     *      <li>Validates and executes the command</li>
     * </ol>
     *
     * @throws OutputException             if occurred output exception
     * @throws ParametersMismatchException if validation fails
     * @throws CommandExecutionException   if exception is thrown during command execution
     */
    public void run() throws OutputException {
        while (true) {
            String inputLine = inputSystem.input();
            logger.info("Input command : " + inputLine);
            CommandReader reader = new CommandReader(inputLine);
            try {
                List<String> params = reader.readCommand();
                logger.info("Parameters : " + String.join(", ", params));
                Command command = commands.get(params.get(0));
                command.setParams(params.subList(1, params.size()));
                if (command.validate()) {
                    command.execute(this);
                } else {
                    outputSystem.sendError();
                    logger.error("Error on validation");
                }
            } catch (Exception e) {
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
    public void deposit(String currency, int denomination, int amount) throws OutputException {
        cashStorage.deposit(currency, denomination, amount);
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
    public void withdraw(String currency, int amount) throws OutputException {
        DenominationStorage cashAfterWithdrawal = cashStorage.withdraw(currency, amount);
        if (cashAfterWithdrawal != null) {
            for (int denomination : cashAfterWithdrawal.getDenominations()) {
                int currentAmount = cashAfterWithdrawal.getAmountByDenomination(denomination);
                // display leftovers
                String infoMessage = String.format("%s %d %d", currency, denomination, currentAmount);
                outputSystem.sendMessage(infoMessage);
                logger.info(infoMessage);
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
    public void print() throws OutputException {
        for (String currency : cashStorage.getCurrencies()) {
            DenominationStorage cashOfCertainCurrency = cashStorage.getCashByCurrency(currency);
            cashOfCertainCurrency.sortAscending();
            for (int denomination : cashOfCertainCurrency.getDenominations()) {
                int amount = cashOfCertainCurrency.getAmountByDenomination(denomination);
                String infoMessage = String.format("%s %d %d", currency, denomination, amount);
                outputSystem.sendMessage(infoMessage);
                logger.info(infoMessage);
            }
        }
        outputSystem.sendOk();
        logger.info("Balance print successful");
    }
}
