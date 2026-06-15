package app.Vue;

import app.Modele.Projectile.ProjectileSimple;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileVue {

    private double size;   //A changer
    private ImageView img;
    private Circle cercle;

    public ProjectileVue(ProjectileSimple projectile) {

        this.img = new ImageView( bonneImage(projectile));
        img.setFitHeight(16);
        img.setFitWidth(16);
        this.cercle = new Circle(projectile.getSize() / 2);
        this.cercle.setFill(Color.ORANGE);

        img.xProperty().bind(projectile.xProperty());
        img.yProperty().bind(projectile.yProperty());
        img.setId("cercle"+projectile.getId());
    }


    public ImageView getProjectileImage() {
        return img;
    }


// Définit l'image du projectible ( choisir la bonne orientation)
    public Image bonneImage(ProjectileSimple p){
        Image img;
        double dx = p.getX() - (p.getCible().getX());
        double dy = p.getX() - (p.getCible().getX());
        if(Math.abs(dx) > Math.abs(dy)/2) {
            System.out.println("horiz");
            if (dx > 0)
                img = new Image("/app/images/pouletProjectible/projectible/gauche.png");
            else
                img = new Image("/app/images/pouletProjectible/projectible/droite.png");
        }
        else{
            System.out.println("vert");
            if (dy > 0)
                img = new Image("/app/images/pouletProjectible/projectible/haut.png");
            else
                img = new Image("/app/images/pouletProjectible/projectible/bas.png");
        }

        return img;
    }

}