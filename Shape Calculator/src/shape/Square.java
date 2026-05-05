package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class Square extends AbstractShape {
    private double side = 0;

    public Square() {
        super("Square", "Side");
    }

    private void inputDimensions(Scanner scanner) throws InvalidInputException {
        while (this.side <= 0) {
            System.out.print("Enter Side length (s): ");
            this.side = InputHandler.getValidatedDoubleInput(scanner);
            if (this.side <= 0) {
                throw new InvalidInputException("Side length must be a positive number.");
            }
        }
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public double calculateVolume() {
        return 0.0;
    }

    @Override
    public double calculateSurfaceArea() {
        return 0.0;
    }

    @Override
    public void displayCalculationOptions() {
        System.out.println("  1. Calculate Area (A=s²)");
        System.out.println("  2. Calculate Perimeter (P=4s)");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double area = calculateArea();
                return String.format("%s: Side=%.2f, Area=%.2f", shapeName, side, area);
            case 2:
                inputDimensions(scanner);
                double perimeter = calculatePerimeter();
                return String.format("%s: Side=%.2f, Perimeter=%.2f", shapeName, side, perimeter);
            default:
                return "Invalid calculation choice.";
        }
    }
}