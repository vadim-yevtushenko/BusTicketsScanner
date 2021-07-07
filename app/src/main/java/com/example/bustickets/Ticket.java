package com.example.bustickets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Ticket implements Serializable {
    private City from;
    private City to;
    private Calendar date;
    private SeatClass seatClass;
    private double price;
    private boolean toilet;
    private Map<FoodStaff, Integer> foodAndDrinks;
    private ArrayList<FoodStaff> foodStaffs;
    private int baggage;
    public static final int NO_BAGGAGE = 0;
    public static final int SMALL_BAGGAGE = 20;
    public static final int BIG_BAGGAGE = 50;

    public Ticket(City from, City to, Calendar date, SeatClass seatClass, double price, boolean toilet) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.seatClass = seatClass;
        this.price = price;
        this.toilet = toilet;
        initFoodAndDrinks();
        this.baggage = NO_BAGGAGE;
        foodStaffs = new ArrayList<>();
    }

    public Ticket() {
    }

    private  void initFoodAndDrinks(){
        foodAndDrinks = new HashMap<>();
        foodAndDrinks.put(FoodStaff.COFFEE, 5);
        foodAndDrinks.put(FoodStaff.TEA, 5);
        foodAndDrinks.put(FoodStaff.JUICE, 10);
        foodAndDrinks.put(FoodStaff.SNACKS, 10);
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBaggage() {
        return baggage;
    }

    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    public Map<FoodStaff, Integer> getFoodAndDrinks() {
        return foodAndDrinks;
    }

    public void setFoodAndDrinks(Map<FoodStaff, Integer> foodAndDrinks) {
        this.foodAndDrinks = foodAndDrinks;
    }

    public ArrayList<FoodStaff> getFoodStaffs() {
        return foodStaffs;
    }

    public void setFoodStaffs(ArrayList<FoodStaff> foodStaffs) {
        this.foodStaffs = foodStaffs;
    }
}
