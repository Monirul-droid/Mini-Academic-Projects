package apputility;

public class LoggerThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println("\n[Logger Thread] Application started successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}