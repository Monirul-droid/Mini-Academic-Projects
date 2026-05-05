package shape;

import java.util.*;
import calculation.ShapeCalculations;
import myexception.InvalidInputException;

public abstract class AbstractShape implements ShapeCalculations {
    protected List<String> dimensions;
    protected String shapeName;

    public AbstractShape(String name) {
        this.shapeName = name;
        this.dimensions = new ArrayList<>();
    }

    public AbstractShape(String name, String... initialDimensions) {
        this.shapeName = name;
        this.dimensions = new ArrayList<>(Arrays.asList(initialDimensions));
    }

    public String getShapeName() {
        return shapeName;
    }

    public abstract void displayCalculationOptions();

    public abstract String performCalculation(int choice, Scanner scanner) throws InvalidInputException;

    @Override
    public String toString() {
        return "Shape: " + shapeName + ", Dimensions Used: " + String.join(", ", dimensions);
    }
}