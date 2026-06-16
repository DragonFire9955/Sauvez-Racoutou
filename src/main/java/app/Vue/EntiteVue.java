package app.Vue;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Barrage.Poubelle;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntiteVue {

    public static int tailleImage = 64;

    public static ImageView appliquerBonneImage(Entite entite, boolean withInit) {

        ImageView imageView;

        if (entite.getName().equalsIgnoreCase("ruchien")) {
            imageView = new ImageView((new Image("app/images/ruchien/img.png")));
        } else {

            if (entite instanceof Animal && !((Animal) entite).isAllie())
                imageView = new ImageView(new Image("app/images/" + entite.getName() + "/img.png"));
            else
                imageView = new ImageView(new Image("app/images/" + entite.getName() + "/niv" + entite.getLevel() + "/img.png"));
        }


        if (withInit)
            initImageView(entite, imageView);

        return imageView;

    }

    public static void upgradeImage(Entite entite, ImageView imageView){

        if(entite instanceof Barrage){
            int taille;
            if(entite.getLevel()==2)
                taille = (int) (tailleImage*1.5);
            else if (entite.getLevel() == 3)
                taille = tailleImage*2;
            else
                taille = tailleImage;
            imageView.setFitWidth(taille);
            imageView.setFitHeight(taille);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        }

        if(entite instanceof Animal && !((Animal) entite).isAllie())
            imageView.setImage(new Image("app/images/"+ entite.getName()+"/img.png"));
        else {
            if (entite.getName().equals("chatCuisinier")) {
                if (entite.getLevel() == 1) {
                    imageView.setFitHeight(tailleImage * 1.22);
                    imageView.setFitWidth(tailleImage * 1.22);

                    imageView.layoutXProperty().unbind();
                    imageView.layoutYProperty(). unbind();

                    imageView.setLayoutX(entite.getX() - imageView.getFitHeight()/2);
                    imageView.setLayoutY(entite.getY() - imageView.getFitHeight()/2);
                } else if (entite.getLevel() == 2) {
                    imageView.setFitHeight(tailleImage);
                    imageView.setFitWidth(tailleImage);
                }
            }
            imageView.setImage(new Image("app/images/"+ entite.getName()+"/niv"+entite.getLevel()+"/img.png"));
        }

    }


    //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus
    public static void initImageView(Entite entite, ImageView imageView) {

        int taille;

        if(entite instanceof Barrage){
            if(entite.getLevel() == 2)
                taille =tailleImage*2;
            else if (entite.getLevel() == 3)
                taille = tailleImage*3;
            else
                taille = tailleImage;
        }

        else if ((!((Animal) entite).isAllie() || (entite instanceof PouletIGPN)))
            taille = (int) (tailleImage*.75);
        else if (entite instanceof Racoutou)
            taille = tailleImage*3;
        else
            taille=tailleImage;

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
        Image img;
        if(entite instanceof Animal && !((Animal) entite).isAllie())
            img = new Image("app/images/"+ entite.getName()+"/degat.gif");
        else
            img = new Image("app/images/"+ entite.getName()+"/niv"+entite.getLevel()+"/degat.gif");
        return img;
    }


    public static Image appliquerImageHypno(Entite e){

        return new Image("app/images/"+ e.getName()+"/hypno.gif");
    }

    public static Image appliquerImageSoin(Entite entite){
        Image img;
        if (entite instanceof Barrage)
            img = new Image("app/images/"+ entite.getName()+"/niv"+entite.getLevel()+"/img.png");   //Les barrages n'ont pas d'images de soin
        else if(entite instanceof Animal && !((Animal) entite).isAllie())
            img = new Image("app/images/"+ entite.getName()+"/soin.gif");
        else
            img = new Image("app/images/"+ entite.getName()+"/niv"+entite.getLevel()+"/soin.gif");
        return img;
    }


}
