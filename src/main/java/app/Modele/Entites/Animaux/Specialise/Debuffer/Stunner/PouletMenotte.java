package app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;

import java.util.List;


public class PouletMenotte extends Stunner {


    public PouletMenotte(double[] coord,GameWorld w) {
        super(coord, 5, 2, 1, 5, 1, 1, w, false, 1, 3, 5, 10);

    }


    @Override
    public List<Animal> getListeAnimaux() {
        return getAnimauxCiblesAccessibles(getRangeEffect(), getWorld().getAllies());
    }
}
