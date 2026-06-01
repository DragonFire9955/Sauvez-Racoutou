package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.GameWorld;

import java.util.List;

public class PouletIGPN extends Buffer {

    public PouletIGPN(double[] coord, GameWorld w) {
        super(coord, 4, 1, 0, 2, 1, 1, w, true, 10, 5, 5);
    }

    @Override
    public void actionBuff() {

    }

    @Override
    public List<Animal> getListeAnimaux() {
        return getAnimauxCiblesAccessibles(getRangeEffect(), getWorld().getEnnemis());
    }
}
