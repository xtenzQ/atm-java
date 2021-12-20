package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public void testWithdrawFull() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 1);
        actual.deposit("USD", 1000, 1);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(5000, 1);
        expected.addByDenomination(1000, 1);

        assertEquals(actual.withdraw("USD", 6000), expected);
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

    @Test
    public void testGetCashByCurrency() {
        CashStorage actualCS = new CashStorage();
        actualCS.deposit("USD", 5000, 100);
        actualCS.deposit("USD", 1000, 1);
        actualCS.deposit("RUR", 500, 1);
        actualCS.deposit("RUR", 100, 7);
        actualCS.deposit("RUR", 10, 100);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(5000, 100);
        expected.addByDenomination(1000, 1);

        DenominationStorage actual = actualCS.getCashByCurrency("USD");

        assertEquals(actual, expected);
    }

    @Test
    public void testGetCurrencies() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 100);
        actual.deposit("RUR", 1000, 1);
        actual.deposit("CHF", 2000, 1);

        Set<String> expected = new HashSet<>(Arrays.asList("USD", "RUR", "CHF"));

        assertEquals(actual.getCurrencies(), expected);
    }

    @Test
    public void testContainsCurrency() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 100);
        actual.deposit("RUR", 1000, 1);
        actual.deposit("CHF", 2000, 1);

        assertTrue(actual.containsCurrency("RUR"));
    }

    @Test
    public void testIsEmpty() {
        CashStorage actual = new CashStorage();
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 100);
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testSize() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 5000, 100);
        actual.deposit("USD", 5000, 10);
        actual.deposit("RUR", 1000, 1);
        actual.deposit("CHF", 2000, 1);

        assertEquals(3, actual.size());
    }

    @Test
    public void testEquals() {
        CashStorage actual = new CashStorage();
        actual.deposit("USD", 100, 10);

        CashStorage expected = new CashStorage();
        expected.deposit("USD", 100, 10);

        assertTrue(actual.equals(expected));
    }

    @Test
    public void testEqualsNotCashStorage() {
        CashStorage actual = new CashStorage();
        assertFalse(actual.equals(0));
    }

    @Test
    public void testEqualsItself() {
        CashStorage actual = new CashStorage();
        assertTrue(actual.equals(actual));
    }
}
