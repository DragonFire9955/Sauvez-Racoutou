package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;

import java.util.ArrayList;
import java.util.List;

public class PouletConservateur extends Buffer {
    public PouletConservateur( GameWorld w) {
        super("Poulet conservateur",  EnnemisSpawn.randomCoord(w), 3, 3, 2, 2, 2, 1, w, false, 5, 5, 10, new ArrayList<>());
        getListeBuff().add(0.5);
    }

}
