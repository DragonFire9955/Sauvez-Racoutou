package app.Vue;

import app.Modele.Projectile.ProjectileSimple;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileVue {

    private double size;   //A changer

    private Circle cercle;

    public ProjectileVue(ProjectileSimple projectile) {

        this.cercle = new Circle(projectile.getSize() / 2);
        this.cercle.setFill(Color.ORANGE);

        cercle.centerXProperty().bind(projectile.xProperty());
        cercle.centerYProperty().bind(projectile.yProperty());
        cercle.setId("cercle"+projectile.getId());
    }

    public Circle getCercle() {
        return cercle;
    }
}