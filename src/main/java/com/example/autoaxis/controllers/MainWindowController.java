package com.example.autoaxis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {


    @FXML
    private StackPane rootContainer;

    @FXML
    private Button homeButton;

    @FXML
    private Button vehiclesButton;

    @FXML
    private Button detailsButton;

    @FXML
    private Button checkoutButton;





    private Button activeButton;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic can be added here if needed
        AppContext.mainWindowController = this; // Set the main window controller in the AppContext

        NavigationManager.getInstance().setRootContainer(rootContainer);
        activeButton = homeButton; // Set the initial active button to homeButton

        try{
            NavigationManager.getInstance().navigateTo("/com/example/pages/Homepage.fxml");
        }catch(Exception e){
            System.out.println("Error loading initial page: " + e.getMessage());

        }
    }



    @FXML
    private void handleHomeClick(){
        if (activeButton == homeButton)return;

        setActiveButton(homeButton);

        NavigationManager.getInstance().navigateTo("/com/example/pages/Homepage.fxml",
                getTransitionDirection(homeButton));
    }

    @FXML
    public void handleVehiclesClick() {
        if (activeButton == vehiclesButton) return;

        setActiveButton(vehiclesButton);
        NavigationManager.getInstance().navigateTo("/com/example/pages/Vehicles.fxml",
                getTransitionDirection(vehiclesButton));
    }

    @FXML
    public void handleSClassDetailsClick() {
        if (activeButton == detailsButton) return;

        setActiveButton(detailsButton);
        NavigationManager.getInstance().navigateTo("/com/example/pages/CarDetails.fxml",
                getTransitionDirection(detailsButton));


    }

    @FXML
    private void handleCheckoutClick() {
        if (activeButton == checkoutButton) return;

        setActiveButton(checkoutButton);
        NavigationManager.getInstance().navigateTo("/com/example/pages/Checkout.fxml",
                getTransitionDirection(checkoutButton));
    }



    public  void setActiveButton(Button newActiveButton) {
        // Remove active class from current button
        if (activeButton != null) {
            activeButton.getStyleClass().remove("active");
        }

        // Add active class to new button
        if (!newActiveButton.getStyleClass().contains("active")) {
            newActiveButton.getStyleClass().add("active");
        }

        activeButton = newActiveButton;
    }

    public void navigateToPage(String pageName) {
        switch (pageName.toLowerCase()) {
            case "home":
                handleHomeClick();
                break;
            case "vehicles":
                handleVehiclesClick();
                break;
            case "details":
                handleSClassDetailsClick();
                break;
            case "checkout":
                handleCheckoutClick();
                break;
        }
    }


    public NavigationManager.TransitionType getTransitionDirection(Button targetButton){

        int currentIndex = getButtonIndex(activeButton);
        int targetIndex = getButtonIndex(targetButton);

        if(targetIndex>currentIndex){
            return NavigationManager.TransitionType.SLIDE_LEFT;
        }
        else{
            return NavigationManager.TransitionType.SLIDE_RIGHT;
        }
    }

    private int getButtonIndex(Button button) {

        if (button == homeButton) return 0;
        if (button == vehiclesButton) return 1;
        if (button == detailsButton) return 2;
        if (button == checkoutButton) return 3;
        return 0;
    }
    public Button getVehiclesButton(){
        return this.vehiclesButton;
    }

    public StackPane getRootContainer() {
        return rootContainer;
    }

    public void setRootContainer(StackPane rootContainer) {
        this.rootContainer = rootContainer;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setVehiclesButton(Button vehiclesButton) {
        this.vehiclesButton = vehiclesButton;
    }

    public Button getDetailsButton() {
        return detailsButton;
    }

    public void setDetailsButton(Button detailsButton) {
        this.detailsButton = detailsButton;
    }

    public Button getCheckoutButton() {
        return checkoutButton;
    }

    public void setCheckoutButton(Button checkoutButton) {
        this.checkoutButton = checkoutButton;
    }

    public Button getActiveButton() {
        return activeButton;
    }
}
