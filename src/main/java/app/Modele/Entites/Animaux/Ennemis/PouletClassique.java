package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Entites.Animaux.Animaux;

import java.util.List;

public class PouletClassique extends Animaux {
    public PouletClassique(double[] coord, GameWorld w) {
        super(coord, 6, 3, 1, 1, w, w.getAllies());
    }

    public PouletClassique( GameWorld w) {
        super(new double[]{200, 200}, 6, 10, 1, 5, w, w.getAllies());

    }

    @Override
    public void update(double dt) {
        super.update(dt);
        this.deplacement();
    }

    @Override
    public Entite getCible() {

        List<Animaux> allies= getWorld().getAllies();
        int i=0;
        while(i<allies.size() && !(allies.get(i) instanceof Racoutou))
            i++;

        if(i==allies.size()) {
            getWorld().changeStateTheEnd();
            return null;
        }

        return allies.get(i);
    }
}
