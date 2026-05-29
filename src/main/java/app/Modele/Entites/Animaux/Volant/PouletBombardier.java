package app.Modele.Entites.Animaux.Volant;

import app.Modele.GameWorld;

public class PouletBombardier extends Volant{
    public PouletBombardier(double[] coord, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(coord, health, vitesse, r, dmg, freqAtk, w, allie);
    }
}
