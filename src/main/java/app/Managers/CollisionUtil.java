package app.Managers;

import app.Entites.Animaux;

public class CollisionUtil {

    private static final double RADIUS = 32; //A définir, c'est la taille des tiles mais faut voir comment on fait

    public static boolean intersects(Animaux a, Animaux b) {
        double dx = (a.getX()+RADIUS/2) - (b.getX()+RADIUS/2);
        double dy = (a.getY()+RADIUS/2) - (b.getY()+RADIUS/2);
        return dx * dx + dy * dy < RADIUS * RADIUS;
    }
}
