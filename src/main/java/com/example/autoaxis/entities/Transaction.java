package com.example.autoaxis.entities;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int userId;
    private int carId;
    private String action; // "BUY" or "RENT"
    private double amount;
    private Timestamp date;

    // Constructor for loading from DB
    public Transaction(int id, int userId, int carId, String action, double amount, Timestamp date) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.action = action;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getCarId() { return carId; }
    public String getAction() { return action; }
    public double getAmount() { return amount; }
    public Timestamp getDate() { return date; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", action='" + action + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
