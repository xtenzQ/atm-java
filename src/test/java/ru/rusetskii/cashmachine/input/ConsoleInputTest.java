package ru.rusetskii.cashmachine.input;

import org.junit.Before;
import org.junit.Test;
import ru.rusetskii.cashmachine.output.ConsoleOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ConsoleInputTest {

    @Test
    public void inputTest() {
        ByteArrayInputStream inContent =
                new ByteArrayInputStream("Test".getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);
        ConsoleInput consoleInput = new ConsoleInput();
        String actual = consoleInput.input();
        assertEquals(actual, "Test");
    }
}