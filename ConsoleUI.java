package com.codegnan.Task1;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI layer separated from main to keep concerns clear.
 */
public class ConsoleUI {
    private final CoffeeService service;
    private final Scanner scanner;

    public ConsoleUI(CoffeeService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1: addCoffee(); break;
                    case 2: listCoffees(); break;
                    case 3: updateMenu(); break;
                    case 4: deleteCoffee(); break;
                    case 5: viewAllLogs(); break;
                    case 6: viewLogsForCoffee(); break;
                    case 7:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.err.println("DB error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Coffee Shop (OOP) ---");
        System.out.println("1. Add Coffee Item");
        System.out.println("2. View All Coffee Items");
        System.out.println("3. Update Coffee Item (choose column)");
        System.out.println("4. Delete Coffee Item");
        System.out.println("5. View All Update History");
        System.out.println("6. View Update History For A Coffee Item");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
    }

    private void addCoffee() throws SQLException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = readDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        Coffee c = new Coffee(name, price, desc);
        int id = service.addCoffee(c);
        System.out.println("Inserted with id: " + id);
    }

    private void listCoffees() throws SQLException {
        List<Coffee> list = service.listAll();
        System.out.printf("%-5s %-20s %-10s %-30s%n", "ID", "Name", "Price", "Description");
        for (Coffee c : list) {
            System.out.printf("%-5d %-20s %-10.2f %-30s%n", c.getId(), c.getName(), c.getPrice(), c.getDescription());
        }
    }

    private void updateMenu() throws SQLException {
        System.out.print("Enter Coffee ID: ");
        int id = readInt();
        System.out.println("1. Name\n2. Price\n3. Description");
        System.out.print("Choose column to update: ");
        int col = readInt();
        scanner.nextLine();
        switch (col) {
            case 1:
                System.out.print("New Name: ");
                String n = scanner.nextLine();
                service.updateColumn(id, "name", n);
                System.out.println("Updated.");
                break;
            case 2:
                System.out.print("New Price: ");
                String p = String.valueOf(readDouble());
                scanner.nextLine();
                service.updateColumn(id, "price", p);
                System.out.println("Updated.");
                break;
            case 3:
                System.out.print("New Description: ");
                String d = scanner.nextLine();
                service.updateColumn(id, "description", d);
                System.out.println("Updated.");
                break;
            default: System.out.println("Invalid column choice.");
        }
    }

    private void deleteCoffee() throws SQLException {
        System.out.print("Enter Coffee ID to delete: ");
        int id = readInt();
        if (service.delete(id)) System.out.println("Deleted.");
        else System.out.println("Not found.");
    }

    private void viewAllLogs() throws SQLException {
        List<UpdateLog> logs = service.getAllLogs();
        System.out.printf("%-7s %-9s %-15s %-20s %-20s %-20s%n", "LogID", "CoffeeID", "Column", "Old Value", "New Value", "Updated At");
        for (UpdateLog l : logs) {
            System.out.printf("%-7d %-9d %-15s %-20s %-20s %-20s%n",
                    l.getLogId(), l.getCoffeeId(), l.getColumnName(),
                    truncate(l.getOldValue(), 18), truncate(l.getNewValue(), 18),
                    l.getUpdatedAt());
        }
    }

    private void viewLogsForCoffee() throws SQLException {
        System.out.print("Enter Coffee ID: ");
        int id = readInt();
        List<UpdateLog> logs = service.getLogsForCoffee(id);
        if (logs.isEmpty()) {
            System.out.println("No logs found for id " + id);
            return;
        }
        System.out.printf("%-7s %-15s %-20s %-20s %-20s%n", "LogID", "Column", "Old Value", "New Value", "Updated At");
        for (UpdateLog l : logs) {
            System.out.printf("%-7d %-15s %-20s %-20s %-20s%n",
                    l.getLogId(), l.getColumnName(), truncate(l.getOldValue(), 18), truncate(l.getNewValue(), 18), l.getUpdatedAt());
        }
    }

    // small helpers
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number: ");
            scanner.nextLine();
        }
        int v = scanner.nextInt();
        scanner.nextLine();
        return v;
    }

    private double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            scanner.nextLine();
        }
        return scanner.nextDouble();
    }

    private String truncate(String s, int max) {
        if (s == null) return "NULL";
        if (s.length() <= max) return s;
        return s.substring(0, max - 3) + "...";
    }
}
