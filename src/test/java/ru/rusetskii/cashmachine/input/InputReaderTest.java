package ru.rusetskii.cashmachine.input;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InputReaderTest {

    @Test
    public void testInput() {
        String test = "test";
        InputStream stream = new ByteArrayInputStream(test.getBytes());
        InputReader inputReader = new InputReader(stream);
        assertEquals(test, inputReader.input());
    }

    @Test
    public void testInputAvailableTrue() {
        String test = "test\ntest";
        InputStream stream = new ByteArrayInputStream(test.getBytes());
        InputReader inputReader = new InputReader(stream);
        assertTrue(inputReader.inputAvailable());
    }

    @Test
    public void testInputAvailableFalse() {
        String test = "test";
        InputStream stream = new ByteArrayInputStream(test.getBytes());
        InputReader inputReader = new InputReader(stream);
        assertTrue(inputReader.inputAvailable());
    }
}