package ru.rusetskii.cashmachine.cash_operation;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DenominationStorageTest {

    @Test
    public void testAddDenominationRaw() {
        DenominationStorage actual = new DenominationStorage();

        Map<Integer, Integer> expected = new TreeMap<>();
        expected.put(10, 2);

        actual.addByDenomination(10, 2);
        assertEquals(actual, expected);
    }

    @Test
    public void testAddDenominationWithSum() {
        DenominationStorage actual = new DenominationStorage();

        Map<Integer, Integer> expected = new TreeMap<>();
        expected.put(10, 7);

        actual.addByDenomination(10, 2);
        actual.addByDenomination(10, 5);
        assertEquals(actual, expected);
    }

    @Test
    public void testSubByDenominationEmpty() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);

        DenominationStorage expected = new DenominationStorage();

        actual.subByDenomination(100, 5);
        assertEquals(actual, expected);
    }

    @Test
    public void testSubByDenominationRaw() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(100, 3);

        actual.subByDenomination(100, 2);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetMoreThanAvailable() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(100, 5);

        actual.subByDenomination(100, 6);

        assertEquals(actual, expected);
    }

    @Test
    public void testContainsDenominationSuccessful() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        assertTrue(actual.containsDenomination(100));
    }

    @Test
    public void testContainsDenominationFailed() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        assertFalse(actual.containsDenomination(10));
    }

    @Test
    public void testGetDenominations() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        actual.addByDenomination(10, 5);
        actual.addByDenomination(1, 5);

        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 10, 100));
        assertEquals(expected, actual.getDenominations());
    }

    @Test
    public void testGetAmountByDenomination() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        assertEquals(5, actual.getAmountByDenomination(100));
    }

    @Test
    public void testIsEmpty() {
        DenominationStorage actual = new DenominationStorage();
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testSize() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        actual.addByDenomination(100, 7);
        actual.addByDenomination(10, 10);
        assertEquals(2, actual.size());
    }

    @Test
    public void testEquals() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        actual.addByDenomination(100, 7);

        DenominationStorage expected = new DenominationStorage();
        expected.addByDenomination(100, 5);
        expected.addByDenomination(100, 7);

        assertEquals(actual, expected);
    }

    @Test
    public void testEqualsMap() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);

        Map<Integer, Integer> expected = new TreeMap<>();
        expected.put(100, 5);

        assertTrue(actual.equals(expected));
    }

    @Test
    public void testEqualsNotDenominationOrMap() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);
        assertFalse(actual.equals(0));
    }

    @Test
    public void testEqualsItself() {
        DenominationStorage actual = new DenominationStorage();
        assertTrue(actual.equals(actual));
    }

    @Test
    public void testClone() {
        DenominationStorage actual = new DenominationStorage();
        actual.addByDenomination(100, 5);

        DenominationStorage clone = actual.clone();
        assertEquals(actual, clone);
    }
}