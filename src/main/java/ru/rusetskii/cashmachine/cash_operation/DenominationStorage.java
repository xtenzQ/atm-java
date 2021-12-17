package ru.rusetskii.cashmachine.cash_operation;

import java.util.*;

/**
 * Represents banknotes of the specific currency stored by its denomination
 * Is unit of a CashStorage class
 * <p>
 * Denomination Storage implements methods to work with it's internal storage which is similar to {@link CashStorage}
 */
public class DenominationStorage implements Cloneable {
    private SortedMap<Integer, Integer> denominationStorage;

    /**
     * Creates {@link TreeMap} to store banknotes in a reverse order to maintain withdrawal principle
     * to withdraw bigger banknotes first
     */
    public DenominationStorage() {
        denominationStorage = new TreeMap<>(Collections.reverseOrder());
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
     * <p>
     * If all banknotes of the specified denomination are withdrawn, remove the denomination from the list
     *
     * @param denomination banknote denomination
     * @param amount cash amount to subtract
     */
    public void subByDenomination(int denomination, int amount) {
        int cashAmountOfCurrentDenomination = getAmountByDenomination(denomination);
        if (cashAmountOfCurrentDenomination == amount) {
            // remove denomination from the storage
            denominationStorage.remove(denomination);
        } else if (cashAmountOfCurrentDenomination > amount) {
            denominationStorage.put(denomination, cashAmountOfCurrentDenomination - amount);
        }
    }
    /**
     * Returns banknote denomination presence status is in the storage
     *
     * @param denomination banknote denomination
     * @return <code>true</code> if denomination is found; <code>false</code> otherwise.
     */
    public boolean contains(int denomination) {
        return denominationStorage.containsKey(denomination);
    }

    /**
     * Returns the set of all banknote denominations
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
     * @return <code>true</code> if empty; <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return (denominationStorage.isEmpty());
    }

    /**
     * Clones {@link DenominationStorage} with its field
     *
     * @return copy of DenominationStorage
     */
    @Override
    public DenominationStorage clone() {
        try {
            // clone Object
            DenominationStorage denominationStorage = (DenominationStorage) super.clone();
            // copy denominationStorage to cloned Object
            denominationStorage.denominationStorage = new TreeMap<>(this.denominationStorage);
            return denominationStorage;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
