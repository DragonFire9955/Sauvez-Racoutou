package app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    @FXML
    private Pane demarragePane;

    @FXML
    public void lancerJeu() throws IOException {

        Pane jeu = FXMLLoader.load(getClass().getResource("/app/main.fxml"));

        demarragePane.getScene().setRoot(jeu);
    }
}