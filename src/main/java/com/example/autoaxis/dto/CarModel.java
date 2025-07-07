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

    public CarModel(String name, String type, String transmission, int seats, boolean isavailable, String path) {
        this.name = name;
        this.type = type;
        this.transmission = transmission;
        this.seats = seats;
        this.hasAC = isavailable;
        this.imageUrl = path;
    }

    public CarModel(int id, String name, String type, String imageUrl, String transmission, int seats, boolean hasAC) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.transmission = transmission;
        this.seats = seats;
        this.hasAC = hasAC;
    }
    public CarModel(int id, String name, String type, String transmission, int seats, boolean hasAC, String imageUrl){
        this.id = id;
        this.name = name;
        this.type = type;
        this.transmission = transmission;
        this.seats = seats;
        this.hasAC = hasAC;
        this.imageUrl = imageUrl;
    }

    public String getIsAvailable() {

        Random random = new Random();
        // Randomly assign AC availability for demonstration purposes
        return random.nextBoolean() ? "Available" : "Not Available";
    }

    private double price;
    private String model;
    private String isAvailable;

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public CarModel() {
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

    public CarModel(int id, String name, String type, String isAvailable, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAvailable = isAvailable;
        this.price = price;
    }
}
