package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.GameWorld;

public class PouletBouclier extends PouletClassique {

    private double bouclier;

    public PouletBouclier(double[] coord, GameWorld w) {

        super(coord, 6, 1, 3, 2, 2, w);
        this.bouclier = 15;
    }

    public PouletBouclier(GameWorld w) {

        super(new double[]{400, 200}, 6, 1, 3, 2, 2, w);
        this.bouclier = 15;
    }

    @Override
    public void update(double dt) {

        super.update(dt);
    }
}
