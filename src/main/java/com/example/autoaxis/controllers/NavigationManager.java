package com.example.autoaxis.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.application.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Stack;

public class NavigationManager {

    private static NavigationManager instance;
    private StackPane rootContainer;// root container that holds the pages
    private Parent currentPage;
    private Stack<String> navigationHistory; //makes a stack of strings that represent the pages(fxmls) that have been loaded

    private NavigationManager() {
        navigationHistory = new Stack<>();
    }

    public static NavigationManager getInstance() {
        if(instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void setRootContainer(StackPane rootContainer) {
        this.rootContainer = rootContainer;
    }

    public void navigateTo(String fxmlPath) {
        navigateTo(fxmlPath, TransitionType.SLIDE_LEFT);
    }

    public void navigateTo(String fxmlPath, TransitionType transitionType){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newPage = loader.load();

            if(currentPage == null) {//if there is no current page, we add the new page to the root container and set it as the current page
                rootContainer.getChildren().add(newPage);
                currentPage = newPage;

                //pushing the new page that has been loaded to the stack of navigation history
                navigationHistory.push(fxmlPath);

                return;

            }
                navigationHistory.push(fxmlPath);
                rootContainer.getChildren().add(newPage);


                //Perform transition based on the type
            performTransition(currentPage, newPage, transitionType);


        } catch (IOException e){
            System.out.println("Error loading FXML: ");
            e.printStackTrace();
        }
    }
    public void navigateBack(){
        if(navigationHistory.size()<=1){
            return; // No previous page to navigate back to
        }

        navigationHistory.pop(); //Remove the current page from the history

        String previousPage = navigationHistory.peek();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(previousPage));
            Parent newPage = loader.load();

            rootContainer.getChildren().add(newPage); // Replace the current page with the previous one

            //Perform transition based on the type
            performTransition(currentPage, newPage, TransitionType.SLIDE_RIGHT);


        } catch(IOException e){
            System.out.println("Error loading previous page: ");
            e.printStackTrace();
        }



    }

    private void performTransition(Parent oldPage, Parent newPage, TransitionType transitionType){
        switch(transitionType){
            case SLIDE_LEFT:
                slideTransition(oldPage, newPage, -rootContainer.getWidth(), 0);
                break;
            case SLIDE_RIGHT:
                slideTransition(oldPage, newPage, rootContainer.getWidth(), 0);
                break;
            case SLIDE_UP:
                slideTransition(oldPage, newPage, 0, -rootContainer.getHeight());
                break;
            case SLIDE_DOWN:
                slideTransition(oldPage, newPage, 0, rootContainer.getHeight());
                break;
            case FADE:
                fadeTransition(oldPage, newPage);
                break;
            case SCALE:
                scaleTransition(oldPage, newPage);
                break;
            case FLIP:
                flipTransition(oldPage, newPage);
                break;
        }
    }

    private void slideTransition(Parent oldPage, Parent newPage, double deltaX, double deltaY) {
        // Implement slide transition logic here
        newPage.setTranslateX(-deltaX);
        newPage.setTranslateY(-deltaY);

        Timeline timeline = new Timeline();

        KeyValue oldPageX = new KeyValue(oldPage.translateXProperty(),deltaX, Interpolator.EASE_BOTH);
        KeyValue oldPageY = new KeyValue(oldPage.translateYProperty(), deltaY, Interpolator.EASE_BOTH);

        // New page animation
        KeyValue newPageX = new KeyValue(newPage.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyValue newPageY = new KeyValue(newPage.translateYProperty(), 0, Interpolator.EASE_BOTH);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(350), oldPageX, oldPageY, newPageX, newPageY);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setOnFinished(e -> {
            rootContainer.getChildren().remove(oldPage);
            oldPage.setTranslateX(0);
            oldPage.setTranslateY(0);
            currentPage = newPage;
        });

        timeline.play();


    }

    private void fadeTransition(Parent oldPage, Parent newPage) {
        newPage.setOpacity(0);

        Timeline timeline = new Timeline();
        KeyValue fadeOut = new KeyValue(oldPage.opacityProperty(), 0, Interpolator.EASE_OUT);
        KeyValue fadeIn = new KeyValue(newPage.opacityProperty(), 1, Interpolator.EASE_IN);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), fadeOut, fadeIn);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setOnFinished(e -> {
            rootContainer.getChildren().remove(oldPage);
            oldPage.setOpacity(1);
            currentPage = newPage;
        });

        timeline.play();
    }

    private void scaleTransition(Parent oldPage, Parent newPage) {
        newPage.setScaleX(0.8);
        newPage.setScaleY(0.8);
        newPage.setOpacity(0);

        Timeline timeline = new Timeline();

        // Old page scales up and fades out
        KeyValue oldScaleX = new KeyValue(oldPage.scaleXProperty(), 1.2, Interpolator.EASE_IN);
        KeyValue oldScaleY = new KeyValue(oldPage.scaleYProperty(), 1.2, Interpolator.EASE_IN);
        KeyValue oldOpacity = new KeyValue(oldPage.opacityProperty(), 0, Interpolator.EASE_IN);

        // New page scales to normal and fades in
        KeyValue newScaleX = new KeyValue(newPage.scaleXProperty(), 1.0, Interpolator.EASE_OUT);
        KeyValue newScaleY = new KeyValue(newPage.scaleYProperty(), 1.0, Interpolator.EASE_OUT);
        KeyValue newOpacity = new KeyValue(newPage.opacityProperty(), 1, Interpolator.EASE_OUT);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(400),
                oldScaleX, oldScaleY, oldOpacity,
                newScaleX, newScaleY, newOpacity);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setOnFinished(e -> {
            rootContainer.getChildren().remove(oldPage);
            oldPage.setScaleX(1);
            oldPage.setScaleY(1);
            oldPage.setOpacity(1);
            currentPage = newPage;
        });

        timeline.play();
    }

    private void flipTransition(Parent oldPage, Parent newPage) {
        // 3D flip effect
        newPage.setScaleX(0);

        Timeline timeline = new Timeline();

        // First half: flip old page
        KeyValue oldFlip = new KeyValue(oldPage.scaleXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame halfWay = new KeyFrame(Duration.millis(200), oldFlip);

        // Second half: flip in new page
        KeyValue newFlip = new KeyValue(newPage.scaleXProperty(), 1, Interpolator.EASE_OUT);
        KeyFrame complete = new KeyFrame(Duration.millis(400), newFlip);

        timeline.getKeyFrames().addAll(halfWay, complete);

        timeline.setOnFinished(e -> {
            rootContainer.getChildren().remove(oldPage);
            oldPage.setScaleX(1);
            currentPage = newPage;
        });

        timeline.play();
    }




    public enum TransitionType {
        SLIDE_LEFT, SLIDE_RIGHT, SLIDE_UP, SLIDE_DOWN, FADE, SCALE, FLIP
    }
}
