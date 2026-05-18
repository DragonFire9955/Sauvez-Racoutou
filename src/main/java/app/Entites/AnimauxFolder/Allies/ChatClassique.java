package app.Entites.AnimauxFolder.Allies;

import app.Entites.Animaux;

public class ChatClassique extends Animaux {
    public ChatClassique(double x, double y, double health, double vitesse, double dmg, double range) {
        super(x, y, health, vitesse, dmg, range);
    }

    @Override
    public void update(double dt) {

    }
}
