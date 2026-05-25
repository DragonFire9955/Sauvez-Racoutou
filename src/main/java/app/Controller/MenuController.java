package app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    public final static Pane stock;
    static {
        try {
            stock = FXMLLoader.load(MenuController.class.getResource("/app/starting.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Pane demarragePane;

    private Controller controller;

    @FXML
    public void lancerJeu() throws IOException {
        System.out.println("bobb");
        Pane jeu = FXMLLoader.load(getClass().getResource("/app/main.fxml"));

        demarragePane.getScene().setRoot(jeu);
    }
}