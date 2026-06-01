package app.Modele.Entites.Animaux.Volants.ChienIntermittent;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class Canon extends Animal {
    protected Canon(double[] coord, double health, int coin, double range, double dmg, double freqAtk, GameWorld w) {
        super(coord, health, 0, range, dmg, freqAtk, w, true);
    }

    @Override
    public Entite getCible() {
        return null;
    }
}
