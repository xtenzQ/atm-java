package ru.rusetskii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            CashMachine cashMachine = new CashMachine();
            cashMachine.run();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Error during Cash Machine execution : " + e);
        }
    }
}
