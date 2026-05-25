package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.List;

public class ChatClassique extends Animaux {
    public ChatClassique(double[] coord, double health, double vitesse, double dmg, double freqAtt, double range, GameWorld w) {
        super(coord, health, vitesse, dmg, range, freqAtt, w);
    }

    public Entite getCible(){
        return null;
    }

    @Override
    public List<Animaux> getListeCibles() {
        return getWorld().getEnnemis();
    }
}
