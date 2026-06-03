package app.Vue;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.Stunner;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 32;

    public static ImageView appliquerBonneImage(Entite entite) {

        ImageView imageView = new ImageView();

        switch (entite) {
            case Racoutou racoutou ->
                imageView.setImage(new Image("app/images/racoutou.png"));
            case ChatScientifique chatScientifique ->
                    imageView.setImage(new Image("app/images/chat.png"));
            case PouletBouclier pouletBouclier ->
                imageView.setImage(new Image("app/images/pouletBouclier.jpg"));
            case Stunner stunner ->
            {
                if (stunner.isAllie())
                    imageView.setImage(new Image("app/images/chatJournaliste.jpg"));
                else
                    imageView.setImage(new Image("app/images/pouletMenotte.jpg"));
            }
            case Animal animal ->
            {
                if (animal.isAllie())
                    imageView.setImage(new Image("app/images/chat.png"));
                else
                    imageView.setImage(new Image("app/images/pouletClassique.png"));
            }
            default -> System.out.println("Image inconnue");

        }

        //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus

        imageView.setFitWidth(tailleImage);
        imageView.setFitHeight(tailleImage);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(""+entite.getId());

        imageView.layoutXProperty().bind(entite.getXProperty().subtract(tailleImage/2));
        imageView.layoutYProperty().bind(entite.getYProperty().subtract(tailleImage/2));

        return imageView;
    }
}
