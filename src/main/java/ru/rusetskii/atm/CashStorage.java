package ru.rusetskii.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CashStorage  {
    // <currency, <denomination, amount>>
    private final Map<String, DenominationStorage> cashStorage = new HashMap<>();

    /**
     * Deposit cash to cash machine
     *
     * @param currency cash currency
     * @param denomination
     * @param amount
     */
    public void addNotes(String currency, int denomination, int amount) {
        DenominationStorage cash;
        if (cashStorage.containsKey(currency)) {
            cash = cashStorage.get(currency);
        }
        else {
            cash = new DenominationStorage();
        }
        cash.addByDenomination(denomination, amount);
        cashStorage.put(currency, cash);
    }

    /**
     * Withdraw cash from cash machine
     *
     * @param currency cash currency
     * @param amount amount of money
     * @return
     */
    public DenominationStorage getCash(String currency, int amount) {
        if (!cashStorage.containsKey(currency)) {
            return null;
        }

        // concurrentModificationException possibility
        DenominationStorage cash = cashStorage.get(currency).clone();
        DenominationStorage newCash = new DenominationStorage();

        for (int value : cashStorage.get(currency).getDenominations()) {
            while (amount >= value && cash.contains(value)) {
                cash.subByDenomination(value, 1);
                newCash.addByDenomination(value, 1);
                amount -= value;
            }
        }

        if (amount == 0) {
            if (cash.isEmpty()) {
                cashStorage.remove(currency);
            } else {
                cashStorage.put(currency, cash);
            }
            return newCash;
        } else {
            return null;
        }
    }

    /**
     *
     *
     * @return
     */
    public Set<String> getCurrencies() {
        return cashStorage.keySet();
    }

    public DenominationStorage getCash(String currency) {
        return cashStorage.get(currency).clone();
    }
}
