package com.example.autoaxis.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(int carId) {
        items.removeIf(item -> item.getCar().getId() == carId);
    }


    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
    public double calculateTotalWithTax() {
        double taxRate = 0.16; // e.g. 16% tax
        return calculateTotal() * (1 + taxRate);
    }


    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", total=" + calculateTotal() +
                '}';
    }
}
