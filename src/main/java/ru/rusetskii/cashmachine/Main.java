package ru.rusetskii.cashmachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rusetskii.cashmachine.input.InputReader;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.OutputSystem;
import ru.rusetskii.cashmachine.output.OutputWriter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * App runner
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    /**
     * Program entry point
     *
     * @param args console arguments
     */
    public static void main(String[] args) {
        InputSystem inputReader = new InputReader(System.in);
        OutputSystem outputWriter = new OutputWriter(System.out);

        try {
            CashMachine cashMachine = new CashMachine(inputReader, outputWriter);
            cashMachine.run();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during Cash Machine execution : " + e);
        }
    }
}
