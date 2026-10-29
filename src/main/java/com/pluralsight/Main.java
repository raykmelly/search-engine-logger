package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        StringBuilder log = new StringBuilder();
        while (running) {
            // Display menu and collect response
            displayMenu();
            String response1 = scanner.nextLine();

            // Check for valid input
            switch (response1.toLowerCase()) {
                case "a":
                    // Launch app
                    logAction(log,"Launch","");
                    break;
                case "b":
                    // Search for term
                    String response2 = promptUser(scanner,"-> Enter a search term:");
                    logAction(log,"Search",response2);
                    break;
                case "c":
                    // Exit app
                    logAction(log,"Exit","");
                    running = false;
                    break;
                default:
                    // User error, invalid command
                    System.out.println("-> Error: invalid command, must be (a,b or c)\n");
                    break;
            }
        }

        // Write to the log.txt file
        writeLog(log);
    }

    public static void logAction(StringBuilder log, String action, String search) {
        // Get the local date and time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        // Create a log entry, add it to logs.txt
        String line;
        if (search.equals("")) {
            line = time.format(format) + " " + action + "\n";
            log.append(line);
        } else {
            line = time.format(format) + " " + action + ": " + search + "\n";
            log.append(line);
        }

        System.out.println("\n[SESSION LOG]");
        System.out.println("-> Entry added to \"log.txt\":\n" + log);
    }

    // Logs the action you take, and a search input
    public static void writeLog(StringBuilder log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt"))) {
            // Write the log
            writer.write(log.toString());
        } catch (IOException e) {
            // File not found
            System.out.println("Error: \"logs.txt\" not found");
        }
    }

    // Displays the home menu on terminal
    public static void displayMenu() {
        System.out.println("[SEARCH ENGINE]");
        System.out.println("-> Choose an action to take from the following:");
        System.out.println("A.) Launch application");
        System.out.println("B.) Search for term");
        System.out.println("C.) Exit");
    }

    // Prompt the user for a string response
    public static String promptUser(Scanner scanner, String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}