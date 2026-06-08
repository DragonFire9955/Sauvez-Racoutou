package app.Vue;

import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 32;

    public static ImageView appliquerBonneImage(Entite entite, boolean withInit) {

        ImageView imageView;
        Image image;

        switch (entite.getName()) {
            case "Racoutou":
                image = new Image("app/images/racoutou.png");
                break;
            case "Chat scientifique":
                image = new Image("app/images/chatScientifique.png");
                break;
            case "Poulet bouclier":
                image = new Image("app/images/pouletBouclier.jpg");
                break;
            case "Chat journaliste":
                image = new Image("app/images/chatJournaliste.jpg");
                break;
            case "Poulet menottes":
                image = new Image("app/images/pouletMenotte.jpg");
                break;
            case "Chat classique":
                image = new Image("app/images/chat.png");
                break;
            case "Poulet classique":
                image = new Image("app/images/pouletClassique.png");
                break;
            default:
                System.out.println("Image inconnue");
                image = null;
                break;
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

        switch (entite.getName()) {
            case "Racoutou":
                    image = new Image("app/images/gif/racoutou.gif");
                    break;
            case "Chat scientifique":
                    image = new Image("app/images/gif/chatScientifique.gif");
                    break;
            case "Poulet bouclier":
                    image = new Image("app/images/gif/pouletBouclier.gif");
                    break;
            case "Chat cuisinier":
                    image = new Image("app/images/gif/chatCuisinier.gif");
                    break;
            case "Chat journaliste":
                image = new Image("app/images/gif/chatJournaliste.gif");
                break;
            case "Poulet menottes":
                image = new Image("app/images/gif/pouletMenottes.gif");
                break;
            case "Chat classique":
                image = new Image("app/images/gif/chatClassique.gif");
                break;
            case "Poulet classique":
                image = new Image("app/images/gif/pouletClassique.gif");
                break;
            default:
                System.out.println("Image inconnue");
                image = null;
                break;
        }

        return image;
    }
}
