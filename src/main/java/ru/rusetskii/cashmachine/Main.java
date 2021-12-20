package ru.rusetskii.cashmachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rusetskii.cashmachine.input.InputReader;
import ru.rusetskii.cashmachine.input.InputSystem;
import ru.rusetskii.cashmachine.output.OutputSystem;
import ru.rusetskii.cashmachine.output.OutputWriter;

/**
 * App runner
 *
 * @author <a href="mailto:rusetscky@outlook.com">Nikita Rusetskii</a>
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during Cash Machine execution : " + e);
        }
    }
}
