package apputility;

import java.util.*;

public class InputHandler {
    public static int getValidatedIntInput(Scanner scanner) {
        int input = -1;
        while (input == -1) {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Error: Invalid input. Please enter a valid number.".concat(" Retrying..."));
                scanner.next();
                System.out.print("Enter your choice: ");
            }
        }
        return input;
    }

    public static double getValidatedDoubleInput(Scanner scanner) {
        double input = -1;
        while (true) {
            try {
                input = scanner.nextDouble();
                return input;
            } catch (InputMismatchException e) {
                System.err.println("Error: Invalid input. Please enter a valid decimal number. Retrying...");
                scanner.next();
            }
        }
    }
}