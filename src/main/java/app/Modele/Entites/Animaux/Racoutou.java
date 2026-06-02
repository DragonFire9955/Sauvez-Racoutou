package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.List;

import static java.lang.Thread.sleep;

public class Racoutou extends Animal {

    public Racoutou(GameWorld w) {
        super(new double[]{685, 375}, 50, 0, 0, 7, 1, 2.5, w, true);
    }

    public Racoutou(double[] coord, GameWorld w) {
        super(coord, 50, 0,  0, 7, 5, 2.5, w, true);
    }

    @Override
    public void update(double dt)  {
        super.update(dt);
    }

    public Entite getCible(){

        List<Animal> cibles = getAnimauxCiblesAccessibles(this.getRange(), getWorld().getEnnemis());

        if (cibles.isEmpty()) return null;

        return cibles.getFirst();
    }



}
