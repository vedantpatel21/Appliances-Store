package com.appliancestore;// This is for Dishwasher 

public class Dishwasher extends Appliance {
    private String feature;
    private String soundRating;

    public Dishwasher(String itemNumber, String brand, int quantity, int wattage, String color, double price, String feature, String soundRating) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.feature = feature;
        this.soundRating = soundRating;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFeature: " + feature + "\nSound Rating: " + soundRating;
    }
}
