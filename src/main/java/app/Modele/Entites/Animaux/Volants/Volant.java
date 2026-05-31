package app.Modele.Entites.Animaux.Volants;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

public class Volant extends Animal {

    public Volant(double[] coord, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(coord, health, vitesse, r, dmg, freqAtk, w, allie);
    }
}
