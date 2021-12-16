package ru.rusetskii;

import ru.rusetskii.input.ConsoleInput;
import ru.rusetskii.input.InputSystem;
import ru.rusetskii.output.ConsoleOutput;
import ru.rusetskii.output.OutputSystem;

public class Main {

    public static void main(String[] args) {
        InputSystem inputSystem = new ConsoleInput(System.in);
        OutputSystem outputSystem = new ConsoleOutput(System.out);

        try {
            CashMachine cashMachine = new CashMachine(inputSystem, outputSystem);
            cashMachine.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
