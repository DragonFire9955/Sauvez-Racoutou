package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class Poubelle extends Barrage {

    public Poubelle(double[] coord, double health, double r, GameWorld w){
        super(coord, health, r, w, 10);
        //rendre static la taille?
    }


    @Override
    public void attaquer() {

    }
}
