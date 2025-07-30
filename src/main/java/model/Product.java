package model;

import exception.InvalidProductException;

public class Product {

    public final int id;
    public double price;
    public String name;
    public String description;

    public Product(int id, double price, String name, String description) throws InvalidProductException {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
