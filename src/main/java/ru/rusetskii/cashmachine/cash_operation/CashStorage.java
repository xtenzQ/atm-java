package ru.rusetskii.cashmachine.cash_operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents storage of Cash Machine and provides working methods. Simple Cash Machine Dispenser should implement the
 * following functions:
 * <ul>
 *     <li>Banknotes deposit;</li>
 *     <li>Banknotes withdrawal.</li>
 * </ul>
 */
public class CashStorage {
    private Map<String, DenominationStorage> cashStorage;

    /**
     * Creates new {@link HashMap} object to store cash information in the format of &lt;Currency, {@link
     * DenominationStorage}&gt; so it allows extracting denomination storage by the currency
     */
    public CashStorage() {
        cashStorage = new HashMap<>();
    }

    /**
     * Adds banknotes of the specified denomination to the cash storage if currency exists or creates new denomination
     * storage and then adds banknotes
     *
     * @param currency     the currency code
     * @param denomination banknote denomination
     * @param amount       cash amount
     * @see DenominationStorage
     */
    public void deposit(String currency, int denomination, int amount) {
        DenominationStorage denominationStorage;
        if (cashStorage.containsKey(currency)) {
            denominationStorage = cashStorage.get(currency);
        } else {
            denominationStorage = new DenominationStorage();
        }
        denominationStorage.addByDenomination(denomination, amount);
        cashStorage.put(currency, denominationStorage);
    }

    /**
     * Returns new denomination storage which represents the amount of money that were withdrawn
     * <p>
     * Method subtracts one banknote of the biggest denomination and adds this banknote to the copied denomination
     * storage. If there are no banknotes of the given denomination, methods removes denomination from the cash
     * storage.
     *
     * @param currency currency code
     * @param amount   amount of cash to withdraw
     * @return amount of cash withdrawn
     */
    public DenominationStorage withdraw(String currency, int amount) {
        if (!cashStorage.containsKey(currency)) return null;

        DenominationStorage denominationStorage = cashStorage.get(currency).clone();
        DenominationStorage withdrawnDenominationStorage = new DenominationStorage();

        for (int denomination : cashStorage.get(currency).getDenominations()) {
            while (denominationStorage.containsDenomination(denomination) && amount >= denomination) {
                denominationStorage.subByDenomination(denomination, 1);
                withdrawnDenominationStorage.addByDenomination(denomination, 1);
                amount = amount - denomination;
            }
        }

        if (amount == 0) {
            if (denominationStorage.isEmpty()) {
                cashStorage.remove(currency);
            } else {
                cashStorage.put(currency, denominationStorage);
            }
            return withdrawnDenominationStorage;
        } else {
            return null;
        }
    }

    /**
     * Returns denomination storage by the given currency
     *
     * @param currency currency code of the banknotes to return
     * @return banknotes of specified currency
     */
    public DenominationStorage getCashByCurrency(String currency) {
        return cashStorage.get(currency).clone();
    }

    /**
     * Returns set of all currencies stored in cash storage
     *
     * @return set of currencies
     */
    public Set<String> getCurrencies() {
        return cashStorage.keySet();
    }

    /**
     * Returns true if currency is found in the storage
     *
     * @param currency the currency code to check
     * @return <code>true</code> if currency exists in the cash storage; <code>false</code> otherwise.
     */
    public boolean containsCurrency(String currency) {
        return cashStorage.containsKey(currency);
    }

    /**
     * Checks storage for emptiness
     *
     * @return <code>true</code> if empty; <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return cashStorage.isEmpty();
    }

    /**
     * Returns size of the cash storage
     *
     * @return cash storage size
     */
    public int size() {
        return cashStorage.size();
    }

    /**
     * Returns CashStorage object
     *
     * @return CashStorage
     */
    private Map<String, DenominationStorage> get() {
        return this.cashStorage;
    }

    /**
     * Compares current object with another CashStorage
     *
     * @param object CashStorage object
     * @return <code>true</code> if objects are equal;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof CashStorage)) {
            return false;
        }

        Map<String, DenominationStorage> objectMap = ((CashStorage) object).get();
        Map<String, DenominationStorage> me = this.get();
        return objectMap.equals(me);
    }
}
