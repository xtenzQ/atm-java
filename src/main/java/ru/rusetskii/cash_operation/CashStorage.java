package ru.rusetskii.cash_operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Contains methods to work with cash machine dispenser
 */
public class CashStorage  {
    // <currency, <denomination, amount>>
    private Map<String, DenominationStorage> cashStorage;

    /**
     * Default constructor
     */
    public CashStorage() {
        cashStorage = new HashMap<>();
    }

    /**
     * Deposit cash to cash machine
     *
     * @param currency cash currency
     * @param denomination
     * @param amount
     */
    public void addNotes(String currency, int denomination, int amount) {
        DenominationStorage denominationStorage;
        if (cashStorage.containsKey(currency)) {
            denominationStorage = cashStorage.get(currency);
        }
        else {
            denominationStorage = new DenominationStorage();
        }
        denominationStorage.addByDenomination(denomination, amount);
        cashStorage.put(currency, denominationStorage);
    }

    /**
     * Gets cash of specified currency
     *
     * @param currency currency code
     * @return DenominationStorage
     */
    public DenominationStorage getCash(String currency) {
        return cashStorage.get(currency).clone();
    }

    /**
     * Withdraw cash from cash machine
     *
     * @param currency cash currency
     * @param amount amount of money
     * @return
     */
    public DenominationStorage getCash(String currency, int amount) {
        if (!cashStorage.containsKey(currency)) return null;

        DenominationStorage denominationStorage = cashStorage.get(currency).clone();
        // used to display the withdrawn amount
        DenominationStorage withdrawnDenominationStorage = new DenominationStorage();

        for (int denomination : cashStorage.get(currency).getDenominations()) {
            while (denominationStorage.contains(denomination) && amount >= denomination) {
                // subtract by denomination if amount is bigger
                denominationStorage.subByDenomination(denomination, 1);
                withdrawnDenominationStorage.addByDenomination(denomination, 1);
                amount = amount - denomination;
            }
        }

        if (amount == 0) {
            if (!denominationStorage.isEmpty()) {
                cashStorage.put(currency, denominationStorage);
            } else {
                cashStorage.remove(currency);
            }
            return withdrawnDenominationStorage;
        } else {
            return null;
        }
    }

    /**
     * Lists all currencies
     *
     * @return set of currencies
     */
    public Set<String> getCurrencies() {
        return cashStorage.keySet();
    }
}
