package app.Vue;

import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import app.Modele.Entites.Entite;

public class VieVue {

    private double largeur = 40.0;
    private double hauteur = 6.0;
    private StackPane conteneur; //pour empiler les 2 rectangles

    public VieVue(Entite entite) {
        this.conteneur = new StackPane();
        this.conteneur.setAlignment(Pos.CENTER_LEFT);
        //pour que le vie se vide vers la gauche

        //fond rouge pour les pv perdus
        Rectangle fondRouge = new Rectangle(largeur, hauteur);
        fondRouge.setFill(Color.RED);

        //dessus vert pour les pv restants
        Rectangle barreVerte = new Rectangle(largeur, hauteur);
        barreVerte.setFill(Color.GREEN);

        //la largeur du rectangle vert est associé a la vie qu'il reste (vie / max * largeur)
        barreVerte.widthProperty().bind(entite.getHealthProperty().divide(entite.getMaxHealth()).multiply(largeur));

        //pour que le rouge soit derriere le vert
        this.conteneur.getChildren().addAll(fondRouge, barreVerte);

        //la vie est décalé par rapport a l'entite
        this.conteneur.layoutXProperty().bind(entite.getXProperty().add(12));
        this.conteneur.layoutYProperty().bind(entite.getYProperty().subtract(12));
    }

    public StackPane getConteneur() {
        return this.conteneur;
    }
}