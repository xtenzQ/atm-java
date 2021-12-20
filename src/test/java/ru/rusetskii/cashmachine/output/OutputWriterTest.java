package ru.rusetskii.cashmachine.output;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertArrayEquals;

public class OutputWriterTest {

    private OutputWriter outputWriter;
    private ByteArrayOutputStream stream;

    @Before
    public void setUp() {
        stream = new ByteArrayOutputStream();
        outputWriter = new OutputWriter(stream);
    }

    @Test
    public void testSendOk() throws OutputException {
        outputWriter.sendOk();
        assertArrayEquals((MessageConstants.OK + "\n").getBytes(), stream.toByteArray());
    }

    @Test
    public void testSendError() throws OutputException {
        outputWriter.sendError();
        assertArrayEquals((MessageConstants.ERROR + "\n").getBytes(), stream.toByteArray());
    }

    @Test
    public void testSendMessage() throws OutputException {
        outputWriter.sendMessage("test");
        assertArrayEquals("test\n".getBytes(), stream.toByteArray());
    }
}