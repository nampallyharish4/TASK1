package com.codegnan.Task1;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer interface to coordinate DAOs and business logic.
 */
public interface CoffeeService {
    int addCoffee(Coffee coffee) throws SQLException;
    List<Coffee> listAll() throws SQLException;
    Coffee getById(int id) throws SQLException;
    boolean updateColumn(int coffeeId, String column, String newValue) throws SQLException;
    boolean delete(int id) throws SQLException;
    List<UpdateLog> getAllLogs() throws SQLException;
    List<UpdateLog> getLogsForCoffee(int coffeeId) throws SQLException;
}
