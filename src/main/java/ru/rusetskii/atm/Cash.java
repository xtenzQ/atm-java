package ru.rusetskii.atm;

import java.util.Collections;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Cash {
    private SortedMap<Integer, Integer> cash = new TreeMap<>(Collections.reverseOrder());

    /**
     *
     * @param value
     * @return
     */
    public boolean contains(int value) {
        return cash.containsKey(value);
    }

    public Set<Integer> getValues() {
        return cash.keySet();
    }

    public int getNumber(int value) {
        return cash.get(value);
    }

    public boolean isEmpty() {
        return (cash.isEmpty());
    }

    /**
     *
     * @param value
     * @param number
     */
    public void put(int value, int number) {
        cash.put(value, number);
    }

    /**
     *
     * @param value
     * @param number
     */
    public void add(int value, int number) {
        if (cash.containsKey(value)) {
            number += cash.get(value);
        }
        put(value, number);
    }

    public boolean pop(int value, int number) {
        int currentNumber = getNumber(value);
        if(currentNumber == number) {
            put(value, currentNumber - number);
        } else if (currentNumber > number) {
            cash.remove(value);
        } else {
            return false;
        }

        return true;
    }

}
