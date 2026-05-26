package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.List;

public class ChatClassique extends Animaux {
    public ChatClassique(double[] coord, double health, double vitesse, double dmg, double freqAtt, double range, GameWorld w) {
        super(coord, health, vitesse, dmg, range, freqAtt, w);
    }

    public ChatClassique(double[] coord, GameWorld w) {
        super(coord, 5, 2, 5, 1, 1, w);
    }

    public Entite getCible(){
        Entite cible= getNearest();
        return cible;
    }

    @Override
    public List<Animaux> getListeCibles() {
        return getWorld().getEnnemis();
    }

    @Override
    public void update(double dt)  {
        super.update(dt);
    }
}
