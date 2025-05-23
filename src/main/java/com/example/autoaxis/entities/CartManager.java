package com.example.autoaxis.entities;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private Map<Integer, Cart> userCarts = new HashMap<>();

    // Get or create cart for a user
    public Cart getCartForUser(int userId) {
        return userCarts.computeIfAbsent(userId, k -> new Cart());
    }

    // Add item to user's cart
    public void addToCart(int userId, Car car, String action) {
        Cart cart = getCartForUser(userId);
        CartItem item = new CartItem(car, action);
        cart.addItem(item);
    }

    // Remove item from user's cart
    public void removeFromCart(int userId, int carId) {
        Cart cart = getCartForUser(userId);
        cart.removeItem(carId);
    }

    // Clear cart for user
    public void clearCart(int userId) {
        Cart cart = getCartForUser(userId);
        cart.clearCart();
    }

    // View cart
    public Cart viewCart(int userId) {
        return getCartForUser(userId);
    }

    // Calculate total with tax
    public double getTotalWithTax(int userId) {
        Cart cart = getCartForUser(userId);
        return cart.calculateTotalWithTax();
    }
}
