package app.Modele.Entites.AnimauxFolder.Ennemis;

import app.Modele.Entites.AnimauxFolder.Allies.Racoutou;
import app.Modele.GameWorld;
import app.Modele.Entites.Animaux;
import app.Modele.CollisionUtil;

public class PouletClassique extends Animaux {
    public PouletClassique(GameWorld w) {
        super(400, 200, 6, 10, 1, 5, w, w.getAllies());
    }

    @Override
    public void update(double dt) {
        System.out.println("TOUJOURS VIVANT EHEHEHH");
        super.update(dt);

        this.deplacement();
        this.handleCollisions();
    }

    public void handleCollisions() {

        int i = 0;
        while (!(this.getWorld().getAllies().get(i) instanceof Racoutou))
            i++;
        Animaux cible = this.getWorld().getAllies().get(i);

        if (CollisionUtil.intersects(this, cible)) {
            //On fera une fonction d'attaque ici, vu que c'est une classe extend par d'autres il faudra la modif dans les enfants
            cible.setHealth(cible.getHealthProperty().getValue()-this.getDamage());
            this.destroy();
        }
    }
}
