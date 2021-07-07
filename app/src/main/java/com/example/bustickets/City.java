package com.example.bustickets;

public enum City {
    ZAPORIZHZHIA("Zaporizhzhia"),
    KIEV("Kiev"),
    LVIV("Lviv");
    private String name;
    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
