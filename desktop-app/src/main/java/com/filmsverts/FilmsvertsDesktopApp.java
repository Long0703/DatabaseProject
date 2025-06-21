package com.filmsverts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.filmsverts.ui.LoginController;

public class FilmsvertsDesktopApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load login screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        
        // Set up the stage
        primaryStage.setTitle("Filmsverts - Login");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 