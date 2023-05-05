package com.example.buildingmaterials;

import Controllers.ScreenController;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BuildingMaterialsApplication extends Application {
    public static Scene primaryScene;
    public static Stage primaryStage;
    public static User currentUser;
    public static FXMLLoader fxmlLoader = new FXMLLoader();
    public static <T> T loadPane(String name) throws IOException {
        var loader = new FXMLLoader();
        fxmlLoader = loader;
        return fxmlLoader.load(Objects.requireNonNull(BuildingMaterialsApplication.class.getResource(name)).openStream());
    }
    public static boolean is_user;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        InitializeScreenController();
        String stylesheet = getClass().getResource("/styles/style.css").toExternalForm();
        primaryScene.getStylesheets().add(stylesheet);
        stage.setScene(primaryScene);
        stage.show();
    }

    private void InitializeScreenController() throws IOException {
        ScreenController.instance.add("login", "login-view.fxml");
        ScreenController.instance.add("registration", "registration-view.fxml");
        ScreenController.instance.add("main", "main-view.fxml");
        ScreenController.instance.add("request", "request-view.fxml");
        ScreenController.instance.activate("login");
    }
    public static void main(String[] args) {
        launch();
    }
}