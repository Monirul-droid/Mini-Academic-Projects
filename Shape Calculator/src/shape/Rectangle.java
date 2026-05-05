package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class Rectangle extends AbstractShape {
    private double length = 0;
    private double width = 0;

    public Rectangle() {
        super("Rectangle", "Length", "Width");
    }

    private void inputDimensions(Scanner scanner) throws InvalidInputException {
        while (this.length <= 0) {
            System.out.print("Enter Length (l): ");
            this.length = InputHandler.getValidatedDoubleInput(scanner);
            if (this.length <= 0) throw new InvalidInputException("Length must be positive.");
        }
        while (this.width <= 0) {
            System.out.print("Enter Width (w): ");
            this.width = InputHandler.getValidatedDoubleInput(scanner);
            if (this.width <= 0) throw new InvalidInputException("Width must be positive.");
        }
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    public double calculatePerimeter() {
        return 2 * (length + width);
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
        System.out.println("  1. Calculate Area (A=lw)");
        System.out.println("  2. Calculate Perimeter (P=2(l+w))");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double area = calculateArea();
                return String.format("%s: L=%.2f, W=%.2f, Area=%.2f", shapeName, length, width, area);
            case 2:
                inputDimensions(scanner);
                double perimeter = calculatePerimeter();
                return String.format("%s: L=%.2f, W=%.2f, Perimeter=%.2f", shapeName, length, width, perimeter);
            default:
                // Invalid choice returns here immediately, before asking for dimensions
                return "Invalid calculation choice.";
        }
    }
}