package app.Vue;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.ChatCuisinier;
import app.Modele.Entites.Animaux.Specialise.Buffer.ChatMedecin;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.Stunner;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 32;

    public static ImageView appliquerBonneImage(Entite entite, boolean withInit) {


        int taille;
        ImageView imageView;
        Image image;

        switch (entite) {
            case Racoutou racoutou -> {
                image = new Image("app/images/racoutou.png");
                taille = tailleImage*2;
            }
            case ChatScientifique chatScientifique ->
                    image = new Image("app/images/chatScientifique.png");
            case PouletBouclier pouletBouclier ->{
                    image = new Image("app/images/pouletBouclier.png");
            }

            case ChatCuisinier chatCuisinier ->
                    image = new Image("app/images/chatCuisinier.png");
            case ChatMedecin chatMedecin ->
                    image = new Image("app/images/chatMedecin.png");
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
        if (entite instanceof Animal && (!((Animal) entite).isAllie() || (entite instanceof PouletIGPN)))
            taille= (int) (tailleImage*.75);
        else if (entite instanceof Racoutou)
            taille = (int) (tailleImage*1.5);
        else
            taille=tailleImage;

        if (withInit)
            initImageView(entite, imageView, taille);

        return imageView;
    }

    //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus
    public static void initImageView(Entite entite, ImageView imageView, int taille) {

        imageView.setFitWidth(taille);
        imageView.setFitHeight(taille);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(""+entite.getId());

        imageView.layoutXProperty().bind(entite.getXProperty().subtract(taille/2));
        imageView.layoutYProperty().bind(entite.getYProperty().subtract(taille/2));
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
