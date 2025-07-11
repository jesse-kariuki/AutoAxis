package com.example.autoaxis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp_SignInController {


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
    private void handleSignIn(ActionEvent event) {

        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/LoginPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setWidth(900);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
