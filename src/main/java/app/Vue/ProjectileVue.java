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

        this.img = new ImageView( new Image("/app/images/pouletProjectible/projectible.png"));
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
}