package com.example.autoaxis.dto;

import java.util.Random;

public class CarModel {
    private int id;
    private String name;
    private String type;
    private String imageUrl;
    private String transmission;
    private int seats;
    private boolean hasAC;
    private double price;
    private String model;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isHasAC() {



        Random random = new Random();
        // Randomly assign AC availability for demonstration purposes
        return hasAC || random.nextBoolean();
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
