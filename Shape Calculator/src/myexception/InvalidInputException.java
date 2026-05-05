package myexception;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super("Invalid Input Error: " + message.toUpperCase());
    }
}