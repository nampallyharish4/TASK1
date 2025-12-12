package com.codegnan.Task1;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO Interface for Coffee operations.
 */
public interface CoffeeDAO {
    int insert(Coffee coffee) throws SQLException;
    List<Coffee> findAll() throws SQLException;
    Coffee findById(int id) throws SQLException;
    boolean update(Coffee coffee) throws SQLException;
    boolean delete(int id) throws SQLException;
}
