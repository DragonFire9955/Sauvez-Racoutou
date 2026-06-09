package app.Modele.Entites.Animaux;

import app.Modele.GameWorld;

import java.util.List;

public class LaTaupe extends Animal {
    private double vitBTP;

    public LaTaupe(double[] coord, GameWorld w, List<Object[]> statsLevels) {
        super("Taupe", coord, w, statsLevels, true);
        this.vitBTP = .5;
    }
}
