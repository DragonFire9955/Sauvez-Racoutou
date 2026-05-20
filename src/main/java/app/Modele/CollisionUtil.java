package app.Modele;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;

public class CollisionUtil {

    private static final double RADIUS = 32; //A définir, c'est la taille des tiles mais faut voir comment on fait

    public static boolean intersects(Entite a, Animaux b) {
        double dx = (a.getX()+RADIUS/2) - (b.getX()+RADIUS/2);
        double dy = (a.getY()+RADIUS/2) - (b.getY()+RADIUS/2);
        return dx * dx + dy * dy < RADIUS * RADIUS;
    }
}
