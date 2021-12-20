package ru.rusetskii.cashmachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rusetskii.cashmachine.cash_operation.CashStorage;
import ru.rusetskii.cashmachine.cash_operation.DenominationStorage;
import ru.rusetskii.cashmachine.command.Processor;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.OutputException;
import ru.rusetskii.cashmachine.output.OutputSystem;

import java.util.NoSuchElementException;

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
 * <p>
 * Commands pattern implementation
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class CashMachine {
    private static final Logger logger = LogManager.getLogger(CashMachine.class.getName());

    private final InputSystem inputSystem;
    private final OutputSystem outputSystem;
    private final CashStorage cashStorage;

    /**
     * Creates Cash Machine object and does initialization
     *
     * @param inputSystem  input byte stream
     * @param outputSystem output byte stream
     */
    public CashMachine(InputSystem inputSystem, OutputSystem outputSystem) {
        this.inputSystem = inputSystem;
        this.outputSystem = outputSystem;
        this.cashStorage = new CashStorage();
    }

    /**
     * Main loop of Cash Machine execution.
     *
     * @throws OutputException if occurred output exception
     */
    public void run() throws OutputException {
        while (inputSystem.inputAvailable()) {
            step();
        }
    }

    /**
     * Single step of running loop *
     *
     * @throws OutputException if occurred output exception
     */
    public void step() throws OutputException {
        String inputLine = inputSystem.input();
        logger.info("Input command : " + inputLine);
        Processor processor = new Processor(this);
        try {
            processor.process(inputLine);
        } catch (InvalidCommandException | NoSuchElementException e) {
            outputSystem.sendError();
            logger.error(e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
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
                String infoMessage = String.format("%d %d", denomination, currentAmount);
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
