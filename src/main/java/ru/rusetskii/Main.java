package ru.rusetskii;

public class Main {

    public static void main(String[] args) {
        try {
            CashMachine cashMachine = new CashMachine();
            cashMachine.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
