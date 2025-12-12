package com.codegnan.Task1;

public class Main {
    public static void main(String[] args) {
        // Initialize DB and tables
        DatabaseConfig.init();

        // Compose service and UI
        CoffeeService service = new CoffeeServiceImpl();
        ConsoleUI ui = new ConsoleUI(service);
        ui.start();
    }
}
