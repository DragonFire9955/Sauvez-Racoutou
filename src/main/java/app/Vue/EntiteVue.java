package app.Vue;

import app.Modele.Entites.Animaux.Allies.ChatClassique;
import app.Modele.Entites.Animaux.Allies.ChatJournaliste;
import app.Modele.Entites.Animaux.Allies.PouletIGPN;
import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Animaux.Ennemis.PouletBouclier;
import app.Modele.Entites.Animaux.Ennemis.PouletClassique;
import app.Modele.Entites.Animaux.Ennemis.PouletMenotte;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {
    public static ImageView appliquerBonneImage(Entite entite) {

        ImageView imageView = new ImageView();

        switch (entite) {
            case Racoutou racoutou ->
                imageView.setImage(new Image("app/images/racoutou.png"));
            case PouletIGPN pouletIGPN ->
                    imageView.setImage(new Image("app/images/chat.png"));
            case ChatJournaliste chatJournaliste ->
                    imageView.setImage(new Image("app/images/chatJournaliste.jpg"));
            case ChatClassique chatClassique ->
                    imageView.setImage(new Image("app/images/chat.png"));
            case PouletBouclier pouletBouclier ->
                imageView.setImage(new Image("app/images/pouletBouclier.jpg"));
            case PouletMenotte pouletMenotte ->
                    imageView.setImage(new Image("app/images/pouletMenotte.jpg"));
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
        imageView.setId(""+entite.getId());
        imageView.layoutXProperty().bind(entite.getXProperty());
        imageView.layoutYProperty().bind(entite.getYProperty());

        return imageView;
    }
}
