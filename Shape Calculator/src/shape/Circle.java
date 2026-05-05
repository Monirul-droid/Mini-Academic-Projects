package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class Circle extends AbstractShape {
    private double radius = 0;

    public Circle() {
        super("Circle", "Radius");
    }

    public Circle(double radius) {
        super("Circle", "Radius");
        this.radius = radius;
    }

    private void inputDimensions(Scanner scanner) throws InvalidInputException {
        while (this.radius <= 0) {
            System.out.print("Enter Radius (r): ");
            this.radius = InputHandler.getValidatedDoubleInput(scanner);
            if (this.radius <= 0) {
                throw new InvalidInputException("Radius must be a positive number.");
            }
        }
    }

    @Override
    public double calculateArea() {
        return calculateArea(this.radius);
    }

    public double calculateArea(double r) {
        return Math.PI * r * r;
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
        System.out.println("  1. Calculate Area (A=πr²)");
        System.out.println("  2. Calculate Circumference (C=2πr)");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double area = calculateArea();
                return String.format("%s: Radius=%.2f, Area=%.2f", shapeName, radius, area);
            case 2:
                inputDimensions(scanner);
                double circumference = 2 * Math.PI * radius;
                return String.format("%s: Radius=%.2f, Circumference=%.2f", shapeName, radius, circumference);
            default:
                return "Invalid calculation choice.";
        }
    }
}