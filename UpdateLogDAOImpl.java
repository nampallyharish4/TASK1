package com.codegnan.Task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateLogDAOImpl implements UpdateLogDAO {

    @Override
    public void insert(UpdateLog log) throws SQLException {
        String sql = "INSERT INTO update_log (coffee_id, column_name, old_value, new_value) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getCoffeeId());
            ps.setString(2, log.getColumnName());
            ps.setString(3, log.getOldValue());
            ps.setString(4, log.getNewValue());
            ps.executeUpdate();
        }
    }

    @Override
    public List<UpdateLog> findAll() throws SQLException {
        String sql = "SELECT log_id, coffee_id, column_name, old_value, new_value, updated_at FROM update_log ORDER BY updated_at DESC";
        List<UpdateLog> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UpdateLog log = new UpdateLog();
                log.setLogId(rs.getInt("log_id"));
                log.setCoffeeId(rs.getInt("coffee_id"));
                log.setColumnName(rs.getString("column_name"));
                log.setOldValue(rs.getString("old_value"));
                log.setNewValue(rs.getString("new_value"));
                log.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(log);
            }
        }
        return list;
    }

    @Override
    public List<UpdateLog> findByCoffeeId(int coffeeId) throws SQLException {
        String sql = "SELECT log_id, coffee_id, column_name, old_value, new_value, updated_at FROM update_log WHERE coffee_id = ? ORDER BY updated_at DESC";
        List<UpdateLog> list = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, coffeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UpdateLog log = new UpdateLog();
                    log.setLogId(rs.getInt("log_id"));
                    log.setCoffeeId(rs.getInt("coffee_id"));
                    log.setColumnName(rs.getString("column_name"));
                    log.setOldValue(rs.getString("old_value"));
                    log.setNewValue(rs.getString("new_value"));
                    log.setUpdatedAt(rs.getTimestamp("updated_at"));
                    list.add(log);
                }
            }
        }
        return list;
    }
}
