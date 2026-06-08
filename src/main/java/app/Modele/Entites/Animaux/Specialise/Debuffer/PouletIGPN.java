package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.GameWorld;

import java.util.List;

public class PouletIGPN extends Debuffer {

    public PouletIGPN(double[] coord, GameWorld w) {
        super("Poulet IGPN", coord, 4, 1, 0, 2, 1, 1, w, true, 1, 5, 5, 10);
    }

    @Override
    public void debuff(double dt) {
        ///  TODO: ralentissement + force --
    }
}
