package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

public class LaTaupe extends Animal {
    private double vitBTP;

    public LaTaupe(double[] coord, GameWorld w) {
        super(coord, 10, 7, .5, 4, 3, w, true);
        this.vitBTP = .5;
    }
}
