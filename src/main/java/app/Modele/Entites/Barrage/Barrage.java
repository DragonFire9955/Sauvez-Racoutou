package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public abstract class Barrage extends Entite {
    public double taille;

    public Barrage(String name, double[] coord, GameWorld w, double health, int coin, double taille){

        super(name, coord, w, StatsEntiteInitialiser.getStatsLevels(name));

        this.taille = taille;

    }

    @Override
    public void update(double dt) {
    }

    public double getTaille(){return taille;}

    public void setTaille(double t){taille=t;}

    public Entite getDirection(){
        return null;
    }


    public abstract int getIdEntite();

    @Override
    public Entite getCible() {
        return getCiblesAccessibles(getRange(), Utilitaire.animauxToEntites(getWorld().getAnimaux())).getFirst();
    }

    public abstract int getIdPoids();

}
