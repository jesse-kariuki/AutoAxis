package com.example.autoaxis.controllers;

import com.example.autoaxis.entities.AuthManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {

    @FXML private TextField roleField;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleSignIn(ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/LoginPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setWidth(900);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAccountCreation(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String username = email.split("@")[0]; // Basic username logic

        // Get role, provide a default if empty
        String role = roleField.getText().trim();
        if (role.isEmpty()) {
            role = "renter"; // Set a default role, e.g., "renter" or "buyer"
            System.out.println("DEBUG: Role field was empty, defaulting to: " + role);
        } else {
            System.out.println("DEBUG: Role from UI: " + role);
        }


        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email and password cannot be empty.");
            return;
        }

        AuthManager authManager = new AuthManager();
        boolean success = authManager.signUp(username, email, password, role);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created! You can now log in.");
            handleSignIn(event); // Redirect to login
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Account creation failed. Try another email.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
