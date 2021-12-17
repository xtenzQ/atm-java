package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashStorageTest {

    @Test
    public void testAddNotesCurrencyExistence() {
        CashStorage cashStorage = new CashStorage();
        cashStorage.addNotes("USD", 10, 10);
        assertTrue(cashStorage.containsKey("USD"));
        assertFalse(cashStorage.containsKey("RUR"));
    }

    @Test
    public void testAddNotesDenominationAmountEquality() {
        CashStorage cashStorage = new CashStorage();
        cashStorage.addNotes("USD", 10, 2);
        cashStorage.addNotes("USD", 100, 5);
        cashStorage.addNotes("CHF", 100, 5);

        assertEquals(12, cashStorage.getCash("USD").getAmountByDenomination(10));
        assertEquals(5, cashStorage.getCash("USD").getAmountByDenomination(100));
        assertEquals(5, cashStorage.getCash("CHF").getAmountByDenomination(100));
    }

    @Test
    public void testGetCash() {
        CashStorage cashStorage = new CashStorage();
        cashStorage.addNotes("USD", 10, 2);
        cashStorage.addNotes("USD", 100, 5);
        cashStorage.addNotes("CHF", 100, 5);
        cashStorage.getCash("USD", 50);
    }

    @Test
    public void getCurrencies() {
    }
}
