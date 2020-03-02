package com.example.listviewtest;

public class Product {
    private String Price;
    private String Name;
    private String Description;

    public Product(String price, String name, String description) {
        Price = price;
        Name = name;
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
