package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CashStorageTest {

    @Test
    public void testDeposit() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 100, 10);
        assertTrue(actual.containsCurrency("USD"));
    }

    @Test
    public void testWithdraw() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 10);
        actual.deposit("USD", 1000, 10);
        actual.deposit("USD", 500, 10);
        actual.deposit("USD", 100, 10);
        actual.deposit("USD", 50, 10);
        actual.deposit("USD", 10, 10);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(5000, 2);
        expected.addByDenomination(1000, 1);
        expected.addByDenomination(100, 2);
        expected.addByDenomination(50, 1);
        expected.addByDenomination(10, 1);

        assertEquals(expected, actual.withdraw("USD", 11260));
    }

    @Test
    public void testWithdrawComplicated() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 100);
        actual.deposit("USD", 1000, 1);
        actual.deposit("USD", 500, 1);
        actual.deposit("USD", 100, 7);
        actual.deposit("USD", 10, 100);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(5000, 2);
        expected.addByDenomination(1000, 1);
        expected.addByDenomination(500, 1);
        expected.addByDenomination(100, 7);
        expected.addByDenomination(10, 6);

        assertEquals(actual.withdraw("USD", 12260), expected);
    }

    @Test
    public void testWithdrawNotEnoughMoney() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 1);
        assertNull(actual.withdraw("USD", 10000));
    }

    @Test
    public void testWithdrawNotFittingDenomination() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 5);
        assertNull(actual.withdraw("USD", 1000));
    }
}
