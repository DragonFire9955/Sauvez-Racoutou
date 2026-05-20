package app.Modele.Entites.AnimauxFolder.Allies;

import app.Modele.Entites.Animaux;
import app.Modele.GameWorld;

public class Racoutou extends Animaux {

    public Racoutou(GameWorld w) {
        super(400, 400, 50, 0, 5, 5, w, w.getEnnemis());
    }

    @Override
    public void update(double dt) {

    }
}
