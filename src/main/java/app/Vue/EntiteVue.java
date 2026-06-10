package app.Vue;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 64;

    public static ImageView appliquerBonneImage(Entite entite, boolean withInit) {


        int taille;
        ImageView imageView;
        Image image;

        switch (entite.getName()) {
            case "Racoutou":
                image = new Image("app/images/racoutou.png");
                break;
// CLASSIQUES
            case "Chat classique":
                image = new Image("app/images/chat.png");
                break;
            case "Poulet classique":
                image = new Image("app/images/pouletClassique.png");
                break;
// SPECIALISES
            case "Poulet bouclier":
                image = new Image("app/images/pouletBouclier/img.png");
                break;
        // BUFFER
            case "chatCuisinier":
                image = new Image("app/images/chatCuisinier/niv0/img.png");
                break;
            case "Chat médecin":
                image = new Image("app/images/chatMedecin/niv0/img.png");
                break;
            case "Poulet conservateur":
                image = new Image("app/images/chienInterim.jpg"); /// TODO: IMG
                break;
        // DEBUFFER
            case "Poulet IGPN":
                image = new Image("app/images/pouletIGPN/img.png");
                break;
            // ALTERATIONS ELEMENTAIRES
            case "Chat scientifique":
                image = new Image("app/images/chatScientifique/niv0/img.png");
                break;
            case "Chat hypnotiseur":
                image = new Image("app/images/chatHypnotiseur/niv0/img.png");
                break;
            // STUNNER
            case "Chat journaliste":
                image = new Image("app/images/chatJournaliste/niv0/img.png");
                break;
            case "Poulet menottes":
                image = new Image("app/images/pouletMenottes/img.png");
                break;
// VOLANTS
            case "Poulet volant":
                image = new Image("app/images/pouletClassique.png"); /// TODO: IMG
                break;
            case "Chien intermittent":
                image = new Image("app/images/chienInterim.jpg"); /// TODO: IMG
                break;
// CREUSANT
            case "Poulet mineur":
                image = new Image("app/images/pouletClassique.png"); /// TODO: IMG
                break;
            case "Taupe":
                image = new Image("app/images/chienInterim.jpg"); /// TODO: IMG
                break;

            default:
                System.out.println(entite.getClass());
                System.out.println("Image inconnue");
                image = null;
                break;
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

        if (entite instanceof Racoutou) {
            System.out.println(" RRRR scale " + imageView.getScaleZ());
            System.out.println("  RRRR translate " + imageView.getTranslateZ());
        }


    }


    public static Image appliquerBonneImageGif(Entite entite) {

        Image image;

        switch (entite.getName()) {
            case "Racoutou":
                    image = new Image("app/images/racoutou/niv0/degat.gif");
                    break;
            case "Chat scientifique":
                    image = new Image("app/images/chatScientifique/niv0/degat.gif");
                    break;
            case "Poulet IGPN":
                image = new Image("app/images/pouletIGPN/degat.gif");
                break;
            case "Poulet bouclier":
                    image = new Image("app/images/pouletBouclier/degat.gif");
                    break;
            case "Chat cuisinier":
                    image = new Image("app/images/chatCuisinier/niv0/degat.gif");
                    break;
            case "Chat journaliste":
                image = new Image("app/images/chatJournaliste/niv0/degat.gif");
                break;
            case "Poulet menottes":
                image = new Image("app/images/pouletMenottes/degat.gif");
                break;
            case "Chat classique":
                image = new Image("app/images/chatClassique/niv0/degat.gif");
                break;
            case "Poulet classique":
                image = new Image("app/images/pouletClassique/degat.gif");
                break;
            default:
                System.out.println("Image inconnue");
                image = null;
                break;
        }

        return image;
    }
}
