package com.codegnan.Task1;

import java.sql.SQLException;
import java.util.List;

public interface UpdateLogDAO {
    void insert(UpdateLog log) throws SQLException;
    List<UpdateLog> findAll() throws SQLException;
    List<UpdateLog> findByCoffeeId(int coffeeId) throws SQLException;
}
