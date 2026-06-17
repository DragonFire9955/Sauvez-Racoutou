package app.Controller;

import app.Modele.Managers.AudioManager;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControlleurMethodesMultiUsages {
    
    
    public static void musiquePrecedente(Label labelTitreMusique) {
        AudioManager.getRadio().pistePrecedente();
        actualiserTitreMusique(labelTitreMusique);
    }

    public static void musiqueSuivante(Label labelTitreMusique) {
        AudioManager.getRadio().pisteSuivante();
        actualiserTitreMusique(labelTitreMusique);
    }

    public static void clicBoutonSon() {
        boolean sonAllume = AudioManager.getRadio().sonActiveProperty().get();
        AudioManager.getRadio().sonActiveProperty().set(!sonAllume);
    }

    public static void actualiserTitreMusique(Label labelTitreMusique) {
        String titre = AudioManager.getRadio().getNomMusiqueActuelle();
        labelTitreMusique.setText(titre);
    }

    public static void lancerJeu(Pane pane, BooleanProperty isStarted) throws IOException {

        Pane jeu = FXMLLoader.load(MenuController.class.getResource("/app/main.fxml"));
        pane.getScene().setRoot(jeu);
        isStarted.setValue(true);

    }

    public static void setVisibleReglages(VBox reglages) {
        reglages.setVisible(!reglages.isVisible());
    }


}
