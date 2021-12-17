package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import static org.junit.Assert.*;

public class DenominationStorageTest {

    @Test
    public void addByDenomination() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 10);
        actual.addByDenomination(100, 2);
        actual.addByDenomination(10, 5);
    }

    @Test
    public void subByDenomination() {
    }
}