package ru.rusetskii.cashmachine;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import ru.rusetskii.cashmachine.input.InputReader;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.OutputSystem;
import ru.rusetskii.cashmachine.output.OutputWriter;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class CashMachineTest {
    private InputSystem inputStream;
    private OutputSystem outputStream;

    @Before
    public void setup() throws IOException {
        inputStream = new InputReader(new FileInputStream("input.txt"));
        outputStream = new OutputWriter(new FileOutputStream("actual.txt"));
    }

    @Test
    public void testRun() throws IOException {
        CashMachine cashMachine = new CashMachine(inputStream, outputStream);
        cashMachine.run();

        outputStream.close();
        inputStream.close();

        Reader actual = new BufferedReader(new FileReader("actual.txt"));
        Reader expected = new BufferedReader(new FileReader("expected.txt"));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(actual, expected));

        actual.close();
        expected.close();
    }
}