package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

public class ChienIntermittent extends Animal {
    public ChienIntermittent(double[] coord, GameWorld w) {
        super(coord, 4, 4, 5, 30, 8, w, true);
    }
}
