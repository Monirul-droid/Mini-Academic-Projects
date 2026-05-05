package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class Cylinder extends AbstractShape {
    private double radius = 0;
    private double height = 0;

    public Cylinder() {
        super("Cylinder", "Radius", "Height");
    }

    private void inputDimensions(Scanner scanner) throws InvalidInputException {
        while (this.radius <= 0) {
            System.out.print("Enter Radius (r): ");
            this.radius = InputHandler.getValidatedDoubleInput(scanner);
            if (this.radius <= 0) throw new InvalidInputException("Radius must be a positive number.");
        }
        while (this.height <= 0) {
            System.out.print("Enter Height (h): ");
            this.height = InputHandler.getValidatedDoubleInput(scanner);
            if (this.height <= 0) throw new InvalidInputException("Height must be a positive number.");
        }
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculateVolume() {
        return calculateArea() * height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * Math.PI * radius * (height + radius);
    }

    @Override
    public void displayCalculationOptions() {
        System.out.println("  1. Calculate Volume (V=πr²h)");
        System.out.println("  2. Calculate Surface Area (A=2πr(h+r))");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double volume = calculateVolume();
                return String.format("%s: R=%.2f, H=%.2f, Volume=%.2f", shapeName, radius, height, volume);
            case 2:
                inputDimensions(scanner);
                double surfaceArea = calculateSurfaceArea();
                return String.format("%s: R=%.2f, H=%.2f, Surface Area=%.2f", shapeName, radius, height, surfaceArea);
            default:
                return "Invalid calculation choice.";
        }
    }
}