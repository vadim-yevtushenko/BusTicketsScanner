package com.example.bustickets;

public enum FoodStaff {
    COFFEE("Coffee"),
    TEA("Tea"),
    JUICE("Juice"),
    SNACKS("Snacks");

    private String name;

    FoodStaff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
