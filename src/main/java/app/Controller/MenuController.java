package app.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    public final static Pane menu;
    public static BooleanProperty isApplicationPlayButtonPressed = new SimpleBooleanProperty(false);

    static {
        try {
            menu = FXMLLoader.load(MenuController.class.getResource("/app/starting.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public Pane demarragePane;

    private Controller controller;

    @FXML
    public void lancerJeu() throws IOException {

        Pane jeu =FXMLLoader.load(MenuController.class.getResource("/app/main.fxml"));
        demarragePane.getScene().setRoot(jeu);
        isApplicationPlayButtonPressed.setValue(true);

    }
}