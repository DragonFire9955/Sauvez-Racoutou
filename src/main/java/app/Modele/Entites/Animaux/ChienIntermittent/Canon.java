package app.Modele.Entites.Animaux.ChienIntermittent;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class Canon extends Animal {
    protected Canon(double[] coord, double health, double range, double dmg, double freqAtk, GameWorld w) {
        super(coord, health, range, dmg, freqAtk, w, true);
    }

    @Override
    public void attaquer() {

    }

    @Override
    public Entite getCible() {
        return null;
    }
}
