import java.util.*;

import apputility.HistoryManager;
import apputility.InputHandler;
import apputility.LoggerThread;
import myexception.InvalidInputException;
import shape.*;


public class ShapeCalculatorApp {
    private final Scanner scanner = new Scanner(System.in);
    private final HistoryManager historyManager = new HistoryManager();

    public void start() {
        new LoggerThread().start();

        int mainChoice = -1;
        while (mainChoice != 3) {
            displayMainMenu();
            mainChoice = InputHandler.getValidatedIntInput(scanner);

            try {
                switch (mainChoice) {
                    case 1:
                        selectShape();
                        break;
                    case 2:
                        historyManager.displayHistory();
                        System.out.print("\nEnter 9 to return to Main Menu: ");
                        while (InputHandler.getValidatedIntInput(scanner) != 9) {
                            System.out.print("Invalid input. Enter 9: ");
                        }
                        break;
                    case 3:
                        System.out.println("Thank you for using the Shape Calculator. Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("\n[FATAL ERROR] An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n==================================");
        System.out.println("  SHAPE CALCULATION APP");
        System.out.println("==================================");
        System.out.println("\nSelect an Option:");
        System.out.println("  1. Select a Shape for Calculation");
        System.out.println("  2. View Calculation History (Detail)");
        System.out.println("  3. Exit Application");
        System.out.print("Enter your choice: ");
    }

    private void selectShape() {
        AbstractShape selectedShape = null;
        int shapeChoice = -1;

        while (shapeChoice != 9) {
            System.out.println("\n--- Select a Shape ---");
            System.out.println("  1. Circle (2D)");
            System.out.println("  2. Square (2D)");
            System.out.println("  3. Rectangle (2D)");
            System.out.println("  4. Rectangular Prism (3D)");
            System.out.println("  5. Sphere (3D)");
            System.out.println("  6. Cylinder (3D)");
            System.out.println("  9. Back to Main Menu");
            System.out.print("Enter your choice: ");

            shapeChoice = InputHandler.getValidatedIntInput(scanner);

            switch (shapeChoice) {
                case 1: selectedShape = new Circle(); break;
                case 2: selectedShape = new Square(); break;
                case 3: selectedShape = new Rectangle(); break;
                case 4: selectedShape = new RectangularPrism(); break;
                case 5: selectedShape = new Sphere(); break;
                case 6: selectedShape = new Cylinder(); break;
                case 9: return;
                default:
                    System.out.println("Invalid shape choice. Please try again.");
                    continue;
            }

            if (selectedShape != null) {
                selectCalculation(selectedShape);
                // Resetting choice to re-display shape menu
                shapeChoice = -1;
            }
        }
    }

    private void selectCalculation(AbstractShape shape) {
        int calcChoice = -1;
        String result = null;
        boolean calculationSuccess = false;
        String errorMessage = null; // Variable to store and display error on next cycle

        while (calcChoice != 9 && !calculationSuccess) {

            // Print previous error message before showing the new menu
            if (errorMessage != null) {
                System.err.println("\n[ERROR] " + errorMessage);
                errorMessage = null; // Clear the error message
            }

            System.out.println("\n--- " + shape.getShapeName().toUpperCase() + " Calculations ---");
            shape.displayCalculationOptions();
            calcChoice = InputHandler.getValidatedIntInput(scanner);

            if (calcChoice == 9) return;

            try {
                result = shape.performCalculation(calcChoice, scanner);

                if (result.startsWith("Invalid")) {
                    // Invalid choice returned from shape class -> save error message for next iteration
                    errorMessage = result;
                } else if (Double.isNaN(result.hashCode())) {
                    throw new ArithmeticException("Calculation resulted in an invalid number (NaN).");
                } else {
                    System.out.println("\n[SUCCESS] Result: " + result);
                    historyManager.addResult(result);
                    calculationSuccess = true;
                }

            } catch (InvalidInputException e) {
                // Invalid dimension input -> save error message for next iteration
                errorMessage = "CUSTOM EXCEPTION CAUGHT: " + e.getMessage();
            } catch (ArithmeticException e) {
                // Arithmetic error -> save error message for next iteration
                errorMessage = "BUILT-IN EXCEPTION CAUGHT: Arithmetic error: " + e.getMessage();
            } catch (Exception e) {
                // General error -> save error message for next iteration
                errorMessage = "RUNTIME ERROR: An unexpected error occurred during calculation: " + e.getMessage();
            }
        }
    }

    public static void main(String[] args) {
        ShapeCalculatorApp app = new ShapeCalculatorApp();
        app.start();
    }
}