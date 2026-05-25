package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class LaTaupe extends ChatClassique {

    private double vitBTP;

    public LaTaupe(double x, double y, GameWorld w) {

        super(x, y, 10, 7, .5, 4, 3, w);
        this.vitBTP = .5;
    }
}
