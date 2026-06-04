package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class PouletConservateur extends Buffer {
    public PouletConservateur(double[] coord, GameWorld w) {
        super(coord, 3, 3, 2, 2, 2, 1, w, false, 5, 5, 10, new ArrayList<>());
        getListeBuff().add(0.5);
    }


    @Override
    public void actionBuff(Animal cible) {
        System.out.println("POULET");
    }
}
