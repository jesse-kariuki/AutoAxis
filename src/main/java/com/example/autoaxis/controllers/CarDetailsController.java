package com.example.autoaxis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CarDetailsController implements Initializable {

    @FXML
    private Button viewAllBtn;

    @FXML
    private StackPane maybackPane;

    @FXML
    private StackPane sClassPane;

    @FXML
    private StackPane glePane;

    @FXML
    private StackPane crownPane;

    @FXML
    private StackPane nineOneOnePane;

    @FXML
    private StackPane landcruiserPane;

    @FXML
    private StackPane cayennePane;



    public CarDetailsController() {}

    @FXML
    private void handleViewAll(){

        AppContext.mainWindowController.handleVehiclesClick();


    }

    @FXML
    public void showMayback(){
        maybackPane.setVisible(true);
        sClassPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showSClass(){
        sClassPane.setVisible(true);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);

        cayennePane.setVisible(false);

    }

    @FXML
    public void showGle(){
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(true);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showNineOneOne(){
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(true);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showCrown(){
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(true);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }
    @FXML
    public void showLandcruiser(){
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(true);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showCayenne(){
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppContext.carDetailsController = this; // Set the car details controller in the AppContext
        maybackPane.setVisible(false); // Initially hide Maybach pane
        sClassPane.setVisible(true); // Initially show S-Class pane
    }
}

