package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.GameWorld;

public class Racoutou extends Animaux {

    public Racoutou(GameWorld w) {
        super(400, 400, 50, 0, 5, 5, w, w.getEnnemis());
    }


}
