package com.example.train_app;

import com.example.train_app.Controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TrainApp extends Application {


    public static Scene primaryScene;
    public static Stage primaryStage;
    public static FXMLLoader fxmlLoader = new FXMLLoader();
    public static <T> T loadPane(String name) throws IOException {
        var loader = new FXMLLoader();
        fxmlLoader = loader;
        return fxmlLoader.load(Objects.requireNonNull(TrainApp.class.getResource(name)).openStream());
    }
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        InitializeScreenController();
        stage.setScene(primaryScene);
        stage.show();
    }
    private void InitializeScreenController() throws IOException {
        ScreenController.instance.add("flights", "flights.fxml");
        ScreenController.instance.add("trains", "trains.fxml");
        ScreenController.instance.add("addTrain", "addTrain.fxml");
        ScreenController.instance.add("addFlight", "addFlight.fxml");
        ScreenController.instance.activate("flights");
    }
    public static void main(String[] args) {
        launch();
    }
}