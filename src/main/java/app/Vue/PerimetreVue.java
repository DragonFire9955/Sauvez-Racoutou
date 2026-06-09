package app.Vue;

import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.YELLOW;

public class PerimetreVue {

    public static Circle initPerimetre(Specialise spe, ImageView img){
        /*Color color;
        switch (spe.getName()) {
            case "Poulet IGPN":
                color = BLUE;
                break;
            default:
                color = YELLOW;
        }

         */


            Circle perimetre = new Circle(img.getX(), img.getY(), spe.getRangeEffect(), YELLOW);
            perimetre.setOpacity(0.15);
            perimetre.setId("perim" + spe.getId());
            perimetre.visibleProperty().bind(spe.getActionSpecialePossible());
            perimetre.layoutXProperty().bind(img.layoutXProperty().add(EntiteVue.tailleImage/4));
            perimetre.layoutYProperty().bind(img.layoutYProperty().add(EntiteVue.tailleImage/2));

        return perimetre;

    }
}
