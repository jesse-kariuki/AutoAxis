package com.example.autoaxis.controllers;

import com.example.autoaxis.entities.AuthManager;
import com.example.autoaxis.entities.User;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class LoginPageController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter email and password.");
            return;
        }

        AuthManager authManager = new AuthManager();
        User loggedInUser = null; // Declare User object to hold the result

        try {
            loggedInUser = authManager.login(email, password); // Use the AuthManager.login method

            if (loggedInUser != null) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");

                AppContext.loggedInUser = loggedInUser; // Store the logged-in user in AppContext

                Parent root = null; // Declare Parent root outside the if-else
                String fxmlPath;
                double stageWidth, stageHeight;
                boolean resizable;

                // Determine which FXML to load based on role
                if (AppContext.role.equalsIgnoreCase("admin")) {
                    System.out.println("DEBUG: User is admin, loading admin page.");
                    fxmlPath = "/com/example/pages/Admin.fxml"; // Path to your admin FXML
                    stageWidth = 908.7; // Example dimensions for admin window
                    stageHeight = 647.3;
                    resizable = true;
                } else {
                    // Default for other roles (renter, buyer, etc.)
                    fxmlPath = "/com/example/pages/MainWindow.fxml";
                    stageWidth = 800;
                    stageHeight = 600;
                    resizable = true;
                }

                root = FXMLLoader.load(getClass().getResource(fxmlPath));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setWidth(stageWidth);
                stage.setHeight(stageHeight);
                stage.setResizable(resizable);
                stage.show();

            } else {
                // AuthManager.login already prints messages for invalid credentials
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password.");
            }
        } catch (IOException e) { // Catch IOException from FXMLLoader.load
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load the next page.");
        }
    }


    @FXML
    public void handleSignUp(ActionEvent event) {
        try {
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/CreateAccount.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene signupScene = new Scene(signupRoot);
            stage.setScene(signupScene);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
