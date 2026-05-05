package shape;

import java.util.*;
import apputility.InputHandler;
import myexception.InvalidInputException;

public class RectangularPrism extends AbstractShape {
    private double length = 0;
    private double width = 0;
    private double height = 0;

    public RectangularPrism() {
        super("Rectangular Prism", "Length", "Width", "Height");
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
        while (this.height <= 0) {
            System.out.print("Enter Height (h): ");
            this.height = InputHandler.getValidatedDoubleInput(scanner);
            if (this.height <= 0) throw new InvalidInputException("Height must be positive.");
        }
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculateVolume() {
        return length * width * height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }

    @Override
    public void displayCalculationOptions() {
        System.out.println("  1. Calculate Volume (V=lwh)");
        System.out.println("  2. Calculate Surface Area (A=2(lw+lh+wh))");
        System.out.println("  9. Back to Shape Selection");
        System.out.print("Select calculation: ");
    }

    @Override
    public String performCalculation(int choice, Scanner scanner) throws InvalidInputException {
        switch (choice) {
            case 1:
                inputDimensions(scanner);
                double volume = calculateVolume();
                return String.format("%s: L=%.2f, W=%.2f, H=%.2f, Volume=%.2f", shapeName, length, width, height, volume);
            case 2:
                inputDimensions(scanner);
                double surfaceArea = calculateSurfaceArea();
                return String.format("%s: L=%.2f, W=%.2f, H=%.2f, Surface Area=%.2f", shapeName, length, width, height, surfaceArea);
            default:
                return "Invalid calculation choice.";
        }
    }
}