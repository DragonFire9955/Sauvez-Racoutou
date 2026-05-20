package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class Poubelle extends Barrage {

    public Poubelle(double x, double y, double health, GameWorld w){
        super(x, y, health, w, 10);
        //rendre static la taille?
    }



}
