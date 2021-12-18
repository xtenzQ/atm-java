package ru.rusetskii.cashmachine.command;

import org.junit.Test;
import ru.rusetskii.cashmachine.command.exception.InvalidCommandException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandReaderTest {

    @Test
    public void testReadCommand() throws Exception {
        String actual = "+ USD 100 10";
        List<String> expected = Arrays.asList("+", "USD", "100", "10");
        CommandReader commandReader = new CommandReader(actual);
        assertEquals(commandReader.readCommand(), expected);
    }

    @Test(expected = InvalidCommandException.class)
    public void testReadCommandTestEmpty() throws Exception {
        CommandReader commandReader = new CommandReader("");
        commandReader.readCommand();
    }
}