package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class ChatClassique extends Animaux {
    public ChatClassique(double x, double y, double health, double vitesse, double range, double dmg, double freqAtk, GameWorld w) {
        super(x, y, health, vitesse, range, dmg, freqAtk, w, w.getEnnemis());
    }

    public Entite getCible(){
        return null;
    }
}
