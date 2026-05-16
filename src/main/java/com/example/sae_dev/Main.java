package com.example.sae_dev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 736);
        stage.setTitle("Sauvez Racoutou");
        stage.setScene(scene);
        stage.show();
    }
}