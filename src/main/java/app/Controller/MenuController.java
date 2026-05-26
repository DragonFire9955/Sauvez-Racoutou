package app.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    public final static Pane menu;
    public static BooleanProperty isGameStarted = new SimpleBooleanProperty(false);

    static {
        try {
            menu = FXMLLoader.load(MenuController.class.getResource("/app/starting.fxml"));
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
        Pane jeu =FXMLLoader.load(MenuController.class.getResource("/app/main.fxml"));
        demarragePane.getScene().setRoot(jeu);
        isGameStarted.setValue(true);

    }
}