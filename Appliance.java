package com.appliancestore;// Appliances Java 

public abstract class Appliance {
    protected String itemNumber;
    protected String brand;
    protected int quantity;
    protected int wattage;
    protected String color;
    protected double price;

    public Appliance(String itemNumber, String brand, int quantity, int wattage, String color, double price) {
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item Number: " + itemNumber + "\nBrand: " + brand + "\nQuantity: " + quantity + "\nWattage: " + wattage + "\nColor: " + color + "\nPrice: $" + price;
    }
}
