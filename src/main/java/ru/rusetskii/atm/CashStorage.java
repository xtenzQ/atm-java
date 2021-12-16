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

    public Cash getCash(String currency, int amount) {
        if (!currencyCash.containsKey(currency)) {
            return null;
        }

        Cash cash = currencyCash.get(currency);
        Cash newCash = new Cash();

        for (int banknotesValue : currencyCash.get(currency).getValues()) {
            while (amount >= banknotesValue && cash.contains(banknotesValue)) {
                cash.pop(banknotesValue, 1);
                newCash.add(banknotesValue, 1);
                amount -= banknotesValue;
            }
        }

        if (amount == 0) {
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

    public Cash getCash(String currencyCode) {
        return currencyCash.get(currencyCode);
    }
}
