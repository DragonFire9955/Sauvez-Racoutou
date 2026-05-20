package app.Modele.Entites.AnimauxFolder.Ennemis;

import app.Modele.GameWorld;
import app.Modele.Entites.Animaux;
import app.Modele.CollisionUtil;

public class PouletClassique extends Animaux {
    public PouletClassique(GameWorld w) {
        super(400, 200, 6, 10, 1, 5, w, w.getAllies());
    }

    @Override
    public void update(double dt) {

        this.deplacement();
        this.handleCollisions();
    }

    public void handleCollisions() {

        if (CollisionUtil.intersects(this, this.getWorld().getAllies().getFirst())) {
            System.out.println("touche !");
            this.destroy();
        }
    }
}
