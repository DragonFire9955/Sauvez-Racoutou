package app.Vue;

import app.Modele.Entites.Animaux;
import app.Modele.Entites.AnimauxFolder.Allies.Racoutou;
import app.Modele.Entites.AnimauxFolder.Ennemis.PouletClassique;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static app.Vue.image.chat;

public class AnimalVue {
    public static ImageView appliquerBonneImage(Animaux animal) {

        ImageView imageView = new ImageView();

        switch (animal) {
            case Racoutou racoutou ->
                imageView.setImage(new Image("app/images/racoutou.png"));
            case PouletClassique pouletClassique ->
                imageView.setImage(new Image("app/images/pouletClassique.png"));
            default -> System.out.println("Image inconnue");
        }

        //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus

        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(""+animal.getId());
        imageView.layoutXProperty().bind(animal.getXProperty());
        imageView.layoutYProperty().bind(animal.getYProperty());

        return imageView;
    }
}
