package com.example.autoaxis.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class car_list_cardController {

    @FXML private Label id_label;
    @FXML private Label name_label;
    @FXML private Label type_label;
    @FXML private Label price_label;
    @FXML private Label status_label;

    public void setData(String id, String name, String type, String price, String status){

        id_label.setText(id);
        name_label.setText(name);
        type_label.setText(type);
        price_label.setText(price);
        status_label.setText(status);
    }
}
