package app.Controller;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Vue.EntiteVue;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import app.Modele.Entites.Entite;

public class VieControlleur {


    private StackPane conteneur; //pour empiler les 2 rectangles

    public VieControlleur(Entite entite, ImageView img) {
        this.conteneur = new StackPane();
        conteneur.setId("visuBarre"+entite.getId());
        this.conteneur.setAlignment(Pos.CENTER_LEFT);
        //pour que le vie se vide vers la gauche

        //fond rouge pour les pv perdus
        Rectangle barreRouge = new Rectangle(img.getFitWidth()/2, img.getFitHeight()/20);
        barreRouge.setFill(Color.RED);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(-1);
        shadow.setColor(Color.WHITESMOKE);
        shadow.setSpread(.5);
        shadow.setRadius(8.);
        barreRouge.setEffect(shadow);


        //dessus vert pour les pv restants
        Rectangle barreVerte = new Rectangle(img.getFitWidth()/2, img.getFitHeight()/20);
        barreVerte.setFill(Color.GREEN);
        barreVerte.widthProperty().bind(entite.getHealthProperty().multiply(img.getFitWidth()/2).divide(entite.getHealthMaxProperty()));

        //la largeur du rectangle vert est associé a la vie qu'il reste (vie / max * largeur)
        //barreVerte.widthProperty().bind(entite.getHealthProperty().divide(entite.getMaxHealth()).multiply(img.getFitWidth()/2));


        //pour que le rouge soit derriere le vert
        this.conteneur.getChildren().addAll(barreRouge, barreVerte);

        //la vie est décalé par rapport a l'entite
        this.conteneur.layoutXProperty().bind(entite.getXProperty().subtract(img.getFitWidth()/4));
        this.conteneur.layoutYProperty().bind(entite.getYProperty().subtract(img.getFitHeight()/2));
        /*
        if(entite instanceof Racoutou)
            this.conteneur.layoutYProperty().bind(entite.getYProperty().subtract(EntiteVue.tailleImage/1.5));
        else
            this.conteneur.layoutYProperty().bind(entite.getYProperty().subtract(EntiteVue.tailleImage/2.5));

         */
    }

    public StackPane getConteneur() {
        return this.conteneur;
    }
}