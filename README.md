# Coffee Shop Management System

A Java Console Application for managing coffee shop operations, including inventory and order processing, using JDBC for database persistence.

## Features
- **Coffee Management**: Add, view, modify, and delete coffee menu items.
- **Inventory Tracking**: Monitor stock levels for coffee ingredients.
- **Order Processing**: Place orders and track usage.
- **Database Persistence**: All data is stored in a MySQL database.

## Prerequisites
- Java Development Kit (JDK) 8 or higher.
- MySQL Database Server.
- MySQL Connector/J (included in `lib/` folder).

## Setup & Configuration

1.  **Database**:
    -   Ensure your MySQL server is running.
    -   Create a database (default configuration expects the database `coffee_shop` or similar, check `DatabaseConfig.java` if needed).
    -   The application uses `root` as the default username and password.
    -   To change credentials, update `src/com/codegnan/Task1/DatabaseConfig.java` or set environment variables `DB_USER` and `DB_PASS`.

2.  **Dependencies**:
    -   The project relies on `mysql-connector-j-8.x.jar`. Ensure it is present in the `lib` directory.

## How to Run

Windows users can use the provided batch script to compile and run the application easily.

1.  Open a terminal in this directory (`src/com/codegnan/Task1`).
2.  Run the following command:
    ```cmd
    run.bat
    ```

This script will compile the Java sources and launch the `Main` class.

## Project Structure
-   `Main.java`: Entry point of the application.
-   `ConsoleUI.java`: Handles user interaction and menu display.
-   `CoffeeService / CoffeeDAO`: Business logic and data access for coffee items.
-   `UpdateLog`: Tracks updates and changes.
-   `DatabaseConfig.java`: Centralized database connection settings.
