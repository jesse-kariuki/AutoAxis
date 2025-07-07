package com.example.autoaxis.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalOrder {
    private CarModel carModel;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private double dailyRate;
    private long rentalDays;
    private double subtotal;
    private double tax;
    private double total;

    // Tax rate constant (16%)
    private static final double TAX_RATE = 0.16;

    public RentalOrder() {}

    public RentalOrder(CarModel carModel, LocalDate pickupDate, LocalDate returnDate, double dailyRate) {
        this.carModel = carModel;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.dailyRate = dailyRate;
        calculatePrices();
    }

    private void calculatePrices() {
        if (pickupDate != null && returnDate != null) {
            this.rentalDays = ChronoUnit.DAYS.between(pickupDate, returnDate);
            this.subtotal = dailyRate * rentalDays;
            this.tax = subtotal * TAX_RATE;
            this.total = subtotal + tax;
        } else {
            this.rentalDays = 0;
            this.subtotal = 0;
            this.tax = 0;
            this.total = 0;
        }
    }

    // Getters and setters
    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
        calculatePrices(); // Recalculate if car model changes and dates are set
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
        calculatePrices(); // Recalculate if pickup date changes
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        calculatePrices(); // Recalculate if return date changes
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
        calculatePrices(); // Recalculate if daily rate changes
    }

    public long getRentalDays() {
        return rentalDays;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public void recalculatePrices() {
        calculatePrices();
    }
}