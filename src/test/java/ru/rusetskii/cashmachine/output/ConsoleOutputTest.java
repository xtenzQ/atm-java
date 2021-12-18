package ru.rusetskii.cashmachine.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSendOk() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.sendOk();
        assertEquals(MessageConstants.OK, outContent.toString().trim());
    }

    @Test
    public void testSendError() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.sendError();
        assertEquals(MessageConstants.ERROR, outContent.toString().trim());
    }

    @Test
    public void testSendMessage() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.sendMessage("AAA");
        assertEquals("AAA", outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}