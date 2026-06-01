package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

public abstract class Barrage extends Entite {
    public double taille;

    public Barrage(double[] coord, double health, int coin, double r, GameWorld w, double t){
        super(coord, health, 5, 0, r, 0, w);
        taille=t;

    }

    @Override
    public void update(double dt) {
    }

    public double getTaille(){return taille;}

    public void setTaille(double t){taille=t;}

    public Entite getCible(){
        return null;
    }


    public abstract int getIdEntite();
}
