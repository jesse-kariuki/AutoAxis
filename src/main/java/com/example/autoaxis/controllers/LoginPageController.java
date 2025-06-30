package com.example.autoaxis.controllers;

import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.autoaxis.entities.User;


import java.io.IOException;

public class LoginPageController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private void handleSignUp(ActionEvent event) {


        try {
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/CreateAccount.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene signupScene = new Scene(signupRoot);
            stage.setScene(signupScene);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleLogin(ActionEvent event) {

    }
}
