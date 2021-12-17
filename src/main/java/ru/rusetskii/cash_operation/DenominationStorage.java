package ru.rusetskii.cash_operation;

import java.util.*;

/**
 * Contains methods to work with the denominations of the specific currency
 * Represents the unit of CashStorage class
 */
public class DenominationStorage implements Cloneable {
    // Stores sorted <denomination, amount> in a reverse order to maintain withdrawal principle to withdraw bigger
    // banknotes first
    private SortedMap<Integer, Integer> denominationStorage;

    /**
     * Default constructor
     */
    public DenominationStorage() {
        denominationStorage = new TreeMap<>(Collections.reverseOrder());
    }

    /**
     * Checks if denomination is in the storage
     *
     * @param denomination banknote denomination
     * @return true if denomination is found, false otherwise
     */
    public boolean contains(int denomination) {
        return denominationStorage.containsKey(denomination);
    }

    /**
     * Lists all available denominations
     *
     * @return set of available denominations
     */
    public Set<Integer> getDenominations() {
        return denominationStorage.keySet();
    }

    /**
     * Returns the amount of banknotes by their denomination
     *
     * @param denomination banknote denomination
     * @return number of banknotes
     */
    public int getAmountByDenomination(int denomination) {
        return denominationStorage.get(denomination);
    }

    /**
     * Checks denomination storage for emptiness
     *
     * @return true if empty, otherwise false
     */
    public boolean isEmpty() {
        return (denominationStorage.isEmpty());
    }

    /**
     * Adds banknotes of the specified denomination
     *
     * @param denomination banknote denomination
     * @param amount       amount of banknotes
     */
    public void addByDenomination(int denomination, int amount) {
        if (denominationStorage.containsKey(denomination)) {
            amount = amount + denominationStorage.get(denomination);
        }
        denominationStorage.put(denomination, amount);
    }

    /**
     * Performs subtraction of banknotes of the specified denomination
     *
     * @param denomination
     * @param amount
     * @return
     */
    public boolean subByDenomination(int denomination, int amount) {
        int cashAmountOfCurrentDenomination = getAmountByDenomination(denomination);
        if (cashAmountOfCurrentDenomination == amount) {
            // remove denomination from the storage
            denominationStorage.remove(denomination);
        } else if (cashAmountOfCurrentDenomination > amount) {
            denominationStorage.put(denomination, cashAmountOfCurrentDenomination - amount);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public DenominationStorage clone() {
        try {
            DenominationStorage denominationStorage = (DenominationStorage) super.clone();
            denominationStorage.denominationStorage = new TreeMap<>(this.denominationStorage);
            return denominationStorage;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
