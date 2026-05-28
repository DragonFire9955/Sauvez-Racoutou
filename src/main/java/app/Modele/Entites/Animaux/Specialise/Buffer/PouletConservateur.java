package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class PouletConservateur extends Buffer {
    public PouletConservateur(double[] coord, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(coord, 3, 2, 2, 2, 1, w, false, 10, 5, 5);
    }

    @Override
    public void actionBuff() {

    }


    @Override
    public List<Animal> getListeAnimaux() {
        return getAnimauxCiblesAccessibles(getRangeEffect(), getWorld().getEnnemis());
    }
}
