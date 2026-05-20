package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.GameWorld;
import app.Modele.Entites.Animaux.Animaux;
import app.Modele.CollisionUtil;

public class PouletClassique extends Animaux {
    public PouletClassique(GameWorld w) {
        super(400, 200, 6, 10, 10, 5, w, w.getAllies());
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        this.deplacement();
    }


}
