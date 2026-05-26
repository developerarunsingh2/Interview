package com.practice.May_June_26;

public class Meal {
    private String name;
    private long price;

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Meal(String name, long price) {
        this.name = name;
        this.price = price;
    }
}
