package com.codegnan.Task1;


public class Coffee {
    private int id;
    private String name;
    private double price;
    private String description;

    public Coffee() {}

    public Coffee(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Coffee(String name, double price, String description) {
        this(0, name, price, description);
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("Coffee{id=%d, name='%s', price=%.2f, description='%s'}",
                id, name, price, description);
    }
}
