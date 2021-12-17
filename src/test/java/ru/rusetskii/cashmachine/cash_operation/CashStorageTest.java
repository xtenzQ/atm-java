package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CashStorageTest {

    @Test
    public void testAddNotesSuccessful() {
        TreeMap<Integer, Integer> expectedDenominationStorageUSD = new TreeMap<>(Collections.reverseOrder());
        expectedDenominationStorageUSD.put(10, 2);
        expectedDenominationStorageUSD.put(100, 7);
        TreeMap<Integer, Integer> expectedDenominationStorageCHF = new TreeMap<>(Collections.reverseOrder());
        expectedDenominationStorageCHF.put(1000, 2);
        DenominationStorage copyExpectedUSD = new DenominationStorage(expectedDenominationStorageUSD);
        DenominationStorage copyExpectedCHF = new DenominationStorage(expectedDenominationStorageCHF);
        Map<String, DenominationStorage> expectedMap = new HashMap<>();
        expectedMap.put("USD", copyExpectedUSD);
        expectedMap.put("CHF", copyExpectedCHF);

        CashStorage actualCashStorage = new CashStorage();
        actualCashStorage.addNotes("USD", 10, 2);
        actualCashStorage.addNotes("USD", 100, 5);
        actualCashStorage.addNotes("USD", 100, 2);
        actualCashStorage.addNotes("CHF", 1000, 2);

        CashStorage expectedCashStorage = new CashStorage(expectedMap);
        assertTrue(actualCashStorage.equals(expectedCashStorage));
    }

    @Test
    public void testGetCashComplicated() {
        TreeMap<Integer, Integer> actualDenominationStorage = new TreeMap<>(Collections.reverseOrder());
        actualDenominationStorage.put(5000, 5);
        actualDenominationStorage.put(1000, 10);
        actualDenominationStorage.put(500, 100);
        actualDenominationStorage.put(100, 20);
        actualDenominationStorage.put(50, 100);
        actualDenominationStorage.put(10, 30);
        DenominationStorage copyActualDS = new DenominationStorage(actualDenominationStorage);
        Map<String, DenominationStorage> actualMap = new HashMap<>();
        actualMap.put("RUR", copyActualDS);
        CashStorage actualStorage = new CashStorage(actualMap);

        TreeMap<Integer, Integer> expectedDenominationStorage = new TreeMap<>();
        expectedDenominationStorage.put(5000, 1);
        expectedDenominationStorage.put(1000, 1);
        expectedDenominationStorage.put(100, 2);
        expectedDenominationStorage.put(10, 2);
        DenominationStorage copyExpectedDenomination = new DenominationStorage(expectedDenominationStorage);

        DenominationStorage actual = actualStorage.getCash("RUR", 6220);
        assertTrue(actual.equals(copyExpectedDenomination));
    }

    @Test
    public void testGetCashEmpty() {
        TreeMap<Integer, Integer> actualDenominationStorage = new TreeMap<>(Collections.reverseOrder());
        actualDenominationStorage.put(5000, 5);
        DenominationStorage copyActualDS = new DenominationStorage(actualDenominationStorage);
        Map<String, DenominationStorage> actualMap = new HashMap<>();
        actualMap.put("RUR", copyActualDS);
        CashStorage actualStorage = new CashStorage(actualMap);

        DenominationStorage actual = actualStorage.getCash("RUR", 25000);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(5000, 5);

        assertEquals(actual.isEmpty(), expected.isEmpty());
    }
}
