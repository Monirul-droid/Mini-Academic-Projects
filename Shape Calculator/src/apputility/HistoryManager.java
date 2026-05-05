package apputility;

import java.io.*;
import java.util.*;

public class HistoryManager {
    private final Deque<String> history = new ArrayDeque<>(5);
    private static final String HISTORY_FILE = "calculation_history.txt";
    private static final int MAX_HISTORY = 5;

    public HistoryManager() {
        // Removed loadHistory() call from constructor to prevent automatic loading/display on startup
    }

    public void addResult(String result) {
        // Ensure history is loaded before adding/saving
        loadHistory();

        if (history.size() >= MAX_HISTORY) {
            history.removeLast();
        }
        history.addFirst(result);
        saveHistory();
    }

    public void displayHistory() {
        // Ensure history is loaded before displaying
        loadHistory();

        System.out.println("\n--- Recent 5 Calculation History ---");
        if (history.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }

        int i = 1;
        for (String entry : history) {
            System.out.println(i++ + ". " + entry);
        }
        System.out.println("------------------------------------");
    }

    private void saveHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (String entry : history) {
                writer.write(entry.replaceAll("\n", " ") + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving history: " + e.getMessage());
        }
    }

    private void loadHistory() {
        // Clear existing memory history before loading from file to prevent duplication
        history.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            List<String> tempHistory = new LinkedList<>();
            while ((line = reader.readLine()) != null) {
                tempHistory.add(0, line);
            }
            for (int i = 0; i < Math.min(MAX_HISTORY, tempHistory.size()); i++) {
                history.addLast(tempHistory.get(i));
            }
        } catch (FileNotFoundException e) {
            // File not found is expected on first run
        } catch (IOException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
    }
}