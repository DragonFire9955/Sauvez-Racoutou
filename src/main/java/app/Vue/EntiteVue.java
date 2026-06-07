package app.Vue;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.ChatCuisinier;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.Stunner;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 32;

    public static ImageView appliquerBonneImage(Entite entite, boolean withInit) {

        ImageView imageView;
        Image image;

        switch (entite) {
            case Racoutou racoutou ->
                    image = new Image("app/images/racoutou.png");
            case ChatScientifique chatScientifique ->
                    image = new Image("app/images/chatScientifique.png");
            case PouletBouclier pouletBouclier ->
                    image = new Image("app/images/pouletBouclier.png");
            case ChatCuisinier chatCuisinier ->
                    image = new Image("app/images/chatCuisinier.png");
            case Stunner stunner ->
            {
                if (stunner.isAllie())
                    image = new Image("app/images/chatJournaliste.png");
                else
                    image = new Image("app/images/pouletMenottes.png");
            }
            case Animal animal ->
            {
                if (animal.isAllie())
                    image = new Image("app/images/chat.png");
                else
                    image = new Image("app/images/pouletClassique.png");
            }
            default -> {
                System.out.println("Image inconnue");
                image = null;
            }
        }

        imageView = new ImageView(image);

        if (withInit)
            initImageView(entite, imageView);

        return imageView;
    }

    //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus
    public static void initImageView(Entite entite, ImageView imageView) {

        imageView.setFitWidth(tailleImage);
        imageView.setFitHeight(tailleImage);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(""+entite.getId());

        imageView.layoutXProperty().bind(entite.getXProperty().subtract(tailleImage/2));
        imageView.layoutYProperty().bind(entite.getYProperty().subtract(tailleImage/2));
    }


    public static Image appliquerBonneImageGif(Entite entite) {

        Image image;

        switch (entite) {
            case Racoutou racoutou ->
                    image = new Image("app/images/gif/racoutou.gif");
            case ChatScientifique chatScientifique ->
                    image = new Image("app/images/gif/chatScientifique.gif");
            case PouletBouclier pouletBouclier ->
                    image = new Image("app/images/gif/pouletBouclier.gif");
            case ChatCuisinier chatCuisinier ->
                    image = new Image("app/images/gif/chatCuisinier.gif");
            case Stunner stunner ->
            {
                if (stunner.isAllie())
                    image = new Image("app/images/gif/chatJournaliste.gif");
                else
                    image = new Image("app/images/gif/pouletMenottes.gif");
            }
            case Animal animal ->
            {
                if (animal.isAllie())
                    image = new Image("app/images/gif/chatClassique.gif");
                else
                    image = new Image("app/images/gif/pouletClassique.gif");
            }
            default -> {
                System.out.println("Image inconnue");
                image = null;
            }

        }

        return image;
    }
}
