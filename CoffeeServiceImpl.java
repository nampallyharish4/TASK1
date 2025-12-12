package com.codegnan.Task1;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of CoffeeService. Coordinates CoffeeDAO and UpdateLogDAO.
 */
public class CoffeeServiceImpl implements CoffeeService {
    private final CoffeeDAO coffeeDAO;
    private final UpdateLogDAO logDAO;

    public CoffeeServiceImpl() {
        this.coffeeDAO = new CoffeeDAOImpl();
        this.logDAO = new UpdateLogDAOImpl();
    }

    @Override
    public int addCoffee(Coffee coffee) throws SQLException {
        return coffeeDAO.insert(coffee);
    }

    @Override
    public List<Coffee> listAll() throws SQLException {
        return coffeeDAO.findAll();
    }

    @Override
    public Coffee getById(int id) throws SQLException {
        return coffeeDAO.findById(id);
    }

    @Override
    public boolean updateColumn(int coffeeId, String column, String newValue) throws SQLException {
        Coffee existing = coffeeDAO.findById(coffeeId);
        if (existing == null) return false;

        String oldValue;
        switch (column.toLowerCase()) {
            case "name":
                oldValue = existing.getName();
                existing.setName(newValue);
                if (coffeeDAO.update(existing)) {
                    logDAO.insert(new UpdateLog(coffeeId, "name", oldValue, newValue));
                    return true;
                }
                return false;
            case "price":
                oldValue = String.valueOf(existing.getPrice());
                existing.setPrice(Double.parseDouble(newValue));
                if (coffeeDAO.update(existing)) {
                    logDAO.insert(new UpdateLog(coffeeId, "price", oldValue, newValue));
                    return true;
                }
                return false;
            case "description":
                oldValue = existing.getDescription();
                existing.setDescription(newValue);
                if (coffeeDAO.update(existing)) {
                    logDAO.insert(new UpdateLog(coffeeId, "description", oldValue, newValue));
                    return true;
                }
                return false;
            default:
                throw new IllegalArgumentException("Unsupported column: " + column);
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return coffeeDAO.delete(id);
    }

    @Override
    public List<UpdateLog> getAllLogs() throws SQLException {
        return logDAO.findAll();
    }

    @Override
    public List<UpdateLog> getLogsForCoffee(int coffeeId) throws SQLException {
        return logDAO.findByCoffeeId(coffeeId);
    }
}
