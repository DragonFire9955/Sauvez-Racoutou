/*
    IMPORTANT : Check si les collisions fonctionnent dans 1 sens et pas dans les 2... car sinon racoutou se défend à la
    range du plus grand, sinon laisser dans les collisions le this.getRange
*/

package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.List;

public class Racoutou extends Animaux {

    public Racoutou(GameWorld w) {
        super(400, 400, 50, 0, 7, 5, 2.5, w, w.getEnnemis());
    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }

    @Override
    public Entite getCible(){

        if (getWorld().getEnnemis().isEmpty()) return null;

        return getWorld().getEnnemis().getFirst();
    }

    @Override
    public List<Animaux> getListeCibles() {
        return getWorld().getEnnemis();
    }
}
