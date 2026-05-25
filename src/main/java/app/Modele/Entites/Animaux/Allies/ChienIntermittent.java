package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChienIntermittent extends ChatClassique {
    public ChienIntermittent(double[] coord, GameWorld w) {
        super(coord, 4, 5, 30, 8, 10, w);
    }
}
