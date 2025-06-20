package com.example.autoaxis.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CheckoutManager {

    private CartManager cartManager;

    public CheckoutManager(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    public boolean checkout(int userId) {
        Cart cart = cartManager.viewCart(userId);
        List<CartItem> items = cart.getItems();

        double total = cart.calculateTotalWithTax();

        for (CartItem item : items) {
            if (!saveTransaction(userId, item)) {
                return false; // fail fast if any save fails
            }
        }

        cartManager.clearCart(userId);
        return true;
    }

    private boolean saveTransaction(int userId, CartItem item) {
        String sql = "INSERT INTO transactions (user_id, car_id, action, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, item.getCar().getId());
            stmt.setString(3, item.getAction());
            stmt.setDouble(4, item.getCar().getPrice()); // or custom price logic

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
            return false;
        }
    }
}
