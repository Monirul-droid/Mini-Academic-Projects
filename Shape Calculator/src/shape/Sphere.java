package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class Sphere extends AbstractShape {
    private double radius = 0;

    public Sphere() {
        super("Sphere", "Radius");
    }

    private void inputDimensions(Scanner scanner) throws InvalidInputException {
        while (this.radius <= 0) {
            System.out.print("Enter Radius (r): ");
            this.radius = InputHandler.getValidatedDoubleInput(scanner);
            if (this.radius <= 0) throw new InvalidInputException("Radius must be a positive number.");
        }
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double calculateSurfaceArea() {
        return calculateArea();
    }

    @Override
    public void displayCalculationOptions() {
        System.out.println("  1. Calculate Volume (V=4/3πr³)");
        System.out.println("  2. Calculate Surface Area (A=4πr²)");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double volume = calculateVolume();
                return String.format("%s: Radius=%.2f, Volume=%.2f", shapeName, radius, volume);
            case 2:
                inputDimensions(scanner);
                double surfaceArea = calculateSurfaceArea();
                return String.format("%s: Radius=%.2f, Surface Area=%.2f", shapeName, radius, surfaceArea);
            default:
                return "Invalid calculation choice.";
        }
    }
}