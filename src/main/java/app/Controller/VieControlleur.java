package app.Controller;

import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import app.Modele.Entites.Entite;

public class VieControlleur {

    private double largeur = 40.0;
    private double hauteur = 6.0;
    private StackPane conteneur;

    public VieControlleur(Entite entite) {
        this.conteneur = new StackPane();
        this.conteneur.setAlignment(Pos.CENTER_LEFT);

        Rectangle fondRouge = new Rectangle(largeur, hauteur);
        fondRouge.setFill(Color.RED);

        Rectangle barreVerte = new Rectangle(largeur, hauteur);
        barreVerte.setFill(Color.GREEN);

        barreVerte.widthProperty().bind(entite.getHealthProperty().divide(entite.getMaxHealth()).multiply(largeur));

        this.conteneur.getChildren().addAll(fondRouge, barreVerte);

        this.conteneur.layoutXProperty().bind(entite.getXProperty().add(12));
        this.conteneur.layoutYProperty().bind(entite.getYProperty().subtract(12));
    }

    public StackPane getConteneur() {
        return this.conteneur;
    }
}