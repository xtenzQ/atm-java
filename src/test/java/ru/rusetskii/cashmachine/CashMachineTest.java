package ru.rusetskii.cashmachine;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import ru.rusetskii.cashmachine.input.InputReader;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.OutputSystem;
import ru.rusetskii.cashmachine.output.OutputWriter;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class CashMachineTest {

    // functional test
    @Test
    public void testRun() throws IOException {

        InputSystem inputStream = new InputReader(new FileInputStream("functest/input.txt"));
        OutputSystem outputStream = new OutputWriter(new FileOutputStream("functest/actual.txt"));

        CashMachine cashMachine = new CashMachine(inputStream, outputStream);
        cashMachine.run();

        outputStream.close();
        inputStream.close();

        Reader actual = new BufferedReader(new FileReader("functest/actual.txt"));
        Reader expected = new BufferedReader(new FileReader("functest/expected.txt"));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(actual, expected));

        actual.close();
        expected.close();
    }
}