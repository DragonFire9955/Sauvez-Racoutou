package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import static java.lang.Thread.sleep;

public class Racoutou extends Animal {

    public Racoutou(GameWorld w) {
        super(new double[]{685, 375}, 10, 0, 7, 5, 1.5, w, true);
    }

    public Racoutou(double[] coord, GameWorld w) {
        super(coord, 50, 0, 7, 5, 2.5, w, true);
    }

    @Override
    public void update(double dt)  {
        super.update(dt);
    }

    public Entite getCible(){

        if (getWorld().getEnnemis().isEmpty()) return null;

        return getWorld().getEnnemis().getFirst();
    }



}
