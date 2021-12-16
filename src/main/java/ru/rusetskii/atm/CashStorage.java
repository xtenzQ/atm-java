package ru.rusetskii.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CashStorage  {
    private Map<String, Cash> currencyCash = new HashMap<>();

    public void addNotes(String currency, int value, int number) {
        Cash cash;
        if (currencyCash.containsKey(currency)) {
            cash = currencyCash.get(currency);
        }
        else {
            cash = new Cash();
        }
        cash.add(value, number);
        currencyCash.put(currency, cash);
    }

    public Cash getCash(String currency, int number) {
        if (!currencyCash.containsKey(currency)) {
            return null;
        }

        // concurrentModificationException possibility
        Cash cash = currencyCash.get(currency).clone();
        Cash newCash = new Cash();

        for (int value : currencyCash.get(currency).getValues()) {
            while (number >= value && cash.contains(value)) {
                cash.pop(value, 1);
                newCash.add(value, 1);
                number -= value;
            }
        }

        if (number == 0) {
            if (cash.isEmpty()) {
                currencyCash.remove(currency);
            } else {
                currencyCash.put(currency, cash);
            }
            return newCash;
        } else {
            return null;
        }
    }

    public Set<String> getValues() {
        return currencyCash.keySet();
    }

    public Cash getCash(String currency) {
        return currencyCash.get(currency).clone();
    }
}
