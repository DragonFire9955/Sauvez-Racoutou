package app.Modele.Entites.Barrage;

import app.Modele.GameWorld;

public class Poubelle extends Barrage {
    private int poids;
    private int id;

    public Poubelle(double[] coord, double health, int coin, double r, GameWorld w){
        super("Poubelle", coord, health, coin, r, w, 10);
        //rendre static la taille?
        poids = 1;
        id = 100;
    }

    public int getIdEntite(){
        return id;
    }

    @Override
    public int getPoids(){
        return poids;
    }

    @Override
    public void attaquer() {

    }

}
