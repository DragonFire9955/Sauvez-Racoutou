package app.Modele.Entites.Animaux.Volants;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

public class Volant extends Animal {

    public Volant(String name, double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(name, coord, health, coin, vitesse, r, dmg, freqAtk, w, allie);
    }
}
