package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public class Poubelle extends Barrage {
    private int poids = 1;
    private int id = 100;

    public Poubelle(double[] coord, double health, int coin, double r, GameWorld w){
        super(coord, health, coin, r, w, 10);
        //rendre static la taille?
    }

    public int getIdEntite(){
        return id;
    }

    public int getpoids(){
        return poids;
    }

    @Override
    public void attaquer() {

    }


}
