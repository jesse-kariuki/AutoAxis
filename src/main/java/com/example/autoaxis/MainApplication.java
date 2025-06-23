package com.example.autoaxis;

import com.example.autoaxis.controllers.AppContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("/com/example/pages/MainWindow.fxml");
        System.out.println("FXML URL: " + url); // Should NOT be null
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("AutoAxis");
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}