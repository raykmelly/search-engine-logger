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
        while (running) {
            // Display menu and collect response
            displayMenu();
            String response = scanner.nextLine();

            // Check for valid input
            switch (response.toLowerCase()) {
                case "a":
                    // Launch app
                    break;
                case "b":
                    // Search for term
                    break;
                case "c":
                    // Exit app
                    break;
                default:
                    // User error, invalid command
                    break;
            }
        }
    }

    public static void logAction(String action, String search) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs.txt"))) {
            // Get the local date and time
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.now();

            // Create a log entry, add it to logs.txt
            String log;
            log = time.format(format) + " " + action + ": " + search + "\n";
            writer.write(log);
        } catch (IOException e) {
            // File not found
            System.out.println("Error: \"logs.txt\" not found");
        }
    }

    public static void displayMenu() {
        System.out.println("[SEARCH ENGINE]");
        System.out.println("-> Choose an action to take from the following:");
        System.out.println("A.) Launch application");
        System.out.println("B.) Search for term");
        System.out.println("C.) Exit");
    }

    public static String promptUser(Scanner scanner, String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}