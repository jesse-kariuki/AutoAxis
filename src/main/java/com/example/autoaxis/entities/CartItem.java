package com.example.autoaxis.entities;

public class CartItem {
    private Car car;
    private String action; // "BUY" or "RENT"

    public CartItem(Car car, String action) {
        this.car = car;
        this.action = action;
    }

    public Car getCar() {
        return car;
    }

    public String getAction() {
        return action;
    }

    public double getTotalPrice() {
        return car.getPrice(); // You can apply tax/discount later
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "car=" + car +
                ", action='" + action + '\'' +
                '}';
    }



}
