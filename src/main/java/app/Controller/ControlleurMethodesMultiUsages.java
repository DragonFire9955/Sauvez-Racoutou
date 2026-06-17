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
        AudioManager.getInstance().pistePrecedente();
        actualiserTitreMusique(labelTitreMusique);
    }

    public static void musiqueSuivante(Label labelTitreMusique) {
        AudioManager.getInstance().pisteSuivante();
        actualiserTitreMusique(labelTitreMusique);
    }

    public static void clicBoutonSon() {
        boolean etatActuel = AudioManager.getInstance().sonActiveProperty().get();
        AudioManager.getInstance().sonActiveProperty().set(!etatActuel);
    }

    public static void actualiserTitreMusique(Label labelTitreMusique) {
        String titre = AudioManager.getInstance().getNomMusiqueActuelle();
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
