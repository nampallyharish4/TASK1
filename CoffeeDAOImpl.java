package com.codegnan.Task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of CoffeeDAO using JDBC.
 */
public class CoffeeDAOImpl implements CoffeeDAO {

    @Override
    public int insert(Coffee coffee) throws SQLException {
        String sql = "INSERT INTO coffee_shop (name, price, description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coffee.getName());
            ps.setDouble(2, coffee.getPrice());
            ps.setString(3, coffee.getDescription());
            int rows = ps.executeUpdate();
            if (rows == 0) return 0;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    coffee.setId(id);
                    return id;
                }
            }
        }
        return 0;
    }

    @Override
    public List<Coffee> findAll() throws SQLException {
        String sql = "SELECT id, name, price, description FROM coffee_shop";
        List<Coffee> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Coffee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }
        }
        return list;
    }

    @Override
    public Coffee findById(int id) throws SQLException {
        String sql = "SELECT id, name, price, description FROM coffee_shop WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Coffee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Coffee coffee) throws SQLException {
        String sql = "UPDATE coffee_shop SET name = ?, price = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coffee.getName());
            ps.setDouble(2, coffee.getPrice());
            ps.setString(3, coffee.getDescription());
            ps.setInt(4, coffee.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM coffee_shop WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
