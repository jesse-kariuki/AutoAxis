package com.example.autoaxis.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

    public List<pkg.Transaction> getTransactionsForUser(int userId) {
        List<pkg.Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pkg.Transaction tx = new pkg.Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("car_id"),
                        rs.getString("action"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("date")
                );
                transactions.add(tx);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }

    // Optional: for admin to get all transactions
    public List<pkg.Transaction> getAllTransactions() {
        List<pkg.Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions ORDER BY date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pkg.Transaction tx = new pkg.Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("car_id"),
                        rs.getString("action"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("date")
                );
                transactions.add(tx);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }
}
