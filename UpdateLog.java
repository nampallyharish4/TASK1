package com.codegnan.Task1;

import java.sql.Timestamp;

public class UpdateLog {
    private int logId;
    private int coffeeId;
    private String columnName;
    private String oldValue;
    private String newValue;
    private Timestamp updatedAt;

    public UpdateLog() {}

    public UpdateLog(int coffeeId, String columnName, String oldValue, String newValue) {
        this.coffeeId = coffeeId;
        this.columnName = columnName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // getters & setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }
    public int getCoffeeId() { return coffeeId; }
    public void setCoffeeId(int coffeeId) { this.coffeeId = coffeeId; }
    public String getColumnName() { return columnName; }
    public void setColumnName(String columnName) { this.columnName = columnName; }
    public String getOldValue() { return oldValue; }
    public void setOldValue(String oldValue) { this.oldValue = oldValue; }
    public String getNewValue() { return newValue; }
    public void setNewValue(String newValue) { this.newValue = newValue; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return String.format("UpdateLog{logId=%d, coffeeId=%d, column='%s', old='%s', new='%s', at=%s}",
                logId, coffeeId, columnName, oldValue, newValue, updatedAt);
    }
}
