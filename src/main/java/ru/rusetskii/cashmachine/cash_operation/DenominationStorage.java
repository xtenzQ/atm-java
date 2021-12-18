package ru.rusetskii.cashmachine.cash_operation;

import java.util.*;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Represents banknotes of the specific currency stored by its denomination
 * Is unit of a {@link CashStorage} class
 * <p>
 * Denomination Storage implements methods to work with its internal storage which is similar to {@link CashStorage}
 *
 * Also, class implements {@link Cloneable} interface to override {@link #clone} method so we can clone its field as well.
 */
public class DenominationStorage implements Cloneable {
    private SortedMap<Integer, Integer> denominationStorage;

    /**
     * Creates {@link TreeMap} to store banknotes in a reverse order to maintain withdrawal principle
     * to withdraw bigger banknotes first
     */
    public DenominationStorage() {
        this.denominationStorage = new TreeMap<>(Collections.reverseOrder());
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
            denominationStorage.remove(denomination);
        } else if (cashAmountOfCurrentDenomination > amount) {
            denominationStorage.put(denomination, cashAmountOfCurrentDenomination - amount);
        }
    }

    /**
     * Returns Denomination Storage object
     *
     * @return Denomination Storage
     */
    private SortedMap<Integer, Integer> get() {
        return this.denominationStorage;
    }

    /**
     * Returns banknote denomination presence status is in the storage
     *
     * @param denomination banknote denomination
     * @return <code>true</code> if denomination is found; <code>false</code> otherwise.
     */
    public boolean containsDenomination(int denomination) {
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
     * Sorts denomination storage by creating new map and then copying it to the new tree map
     */
    public void sortAscending() {
        // looks awful but it works
        denominationStorage = new TreeMap<>(new HashMap<>(denominationStorage));
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
     * Returns the size of denomination storage
     *
     * @return size of the map
     */
    public int size() {
        return denominationStorage.size();
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

    /**
     * Compares current object with another Map or DenominationStorage
     *
     * @param object another DenominationStorage object
     * @return <code>true</code> if objects are equal; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof DenominationStorage || object instanceof Map)) {
            return false;
        }

        if (object instanceof Map) {
            return object.equals(this.get());
        }
        else {
            SortedMap<Integer, Integer> objectMap = ((DenominationStorage) object).get();
            SortedMap<Integer, Integer> me = this.get();
            return objectMap.equals(me);
        }
    }
}
