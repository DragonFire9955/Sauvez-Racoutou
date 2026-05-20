package app.Modele.Entites.AnimauxFolder.Allies;

import app.Modele.Entites.Animaux;
import app.Modele.GameWorld;

public class ChatClassique extends Animaux {
    public ChatClassique(double x, double y, double health, double vitesse, double dmg, double range, GameWorld w) {
        super(x, y, health, vitesse, dmg, range, w, w.getEnnemis());
    }

    @Override
    public void update(double dt) {

        this.deplacement();
    }
}
