package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.GameWorld;

public class PouletBouclier extends PouletClassique {

    private double bouclier;

    public PouletBouclier(int x, int y, GameWorld w) {

        super(x, y, 6, 7, 3, 2, w);
        this.bouclier = 15;
    }
}
