package app.Vue;

import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Entite;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.*;

public class PerimetreVue {

    private Pane carte;

    private Entite e;

    private Circle p;
    private Circle pSpe;

    public PerimetreVue(Pane carte, Entite e){

        this.carte = carte;

        this.e = e;

        p = new Circle();
        pSpe = new Circle();
    }

    public  void initPerimetre(Entite e, ImageView img){
        /*Color color;
        switch (spe.getName()) {
            case "Poulet IGPN":
                color = BLUE;
                break;
            default:
                color = YELLOW;
        }

         */


            p = new Circle(img.getX(), img.getY(), e.getRange(), YELLOW);
            p.setOpacity(0.15);
            p.setId("perim" + e.getId());
            p.setVisible(false);
            p.layoutXProperty().bind(img.layoutXProperty().add(EntiteVue.tailleImage/4));
            p.layoutYProperty().bind(img.layoutYProperty().add(EntiteVue.tailleImage/2));
            /*
            p.layoutXProperty().bind(e.getXProperty().add(EntiteVue.tailleImage/4));
            p.layoutYProperty().bind(e.getYProperty().add(EntiteVue.tailleImage/2));

             */

            carte.getChildren().add(1, p);

            if ((e instanceof Specialise) /*|| (e instanceof ChatHypnotiseur)*/){
                Circle perimSpe = new Circle(img.getX(), img.getY(), (double) e.getStatsLevels().get(e.getLevel())[7], GREEN);
                perimSpe.setOpacity(0.15);
                perimSpe.setId("perimSpe" + e.getId());
                perimSpe.setVisible(false);
                perimSpe.layoutXProperty().bind(img.layoutXProperty().add(EntiteVue.tailleImage / 4));
                perimSpe.layoutYProperty().bind(img.layoutYProperty().add(EntiteVue.tailleImage / 2));
                carte.getChildren().add(1, perimSpe);

                pSpe = perimSpe;
            }

        }

        public void changeVisibility(){

            p.setVisible(!p.isVisible());

            if (pSpe != null)
                pSpe.setVisible(!pSpe.isVisible());

        }
}
