package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public abstract class Barrage extends Entite {
    public int taille;
    private int idPoids;
    private int id;

    public Barrage(String name, double[] coord, GameWorld w, int actualLevel, double health, int coin, int taille, int idPoids, int id){

        super(name, coord, w, StatsEntiteInitialiser.getStatsLevels(name), actualLevel);

        this.taille = taille;
        this.idPoids = idPoids;
        this.id = id;

    }

    @Override
    public void update(double dt) {
    }

    public int getTaille(){return taille;}

    public void setTaille(int t){taille=t;}

    public Entite getDirection(){
        return null;
    }


    @Override
    public Entite getCible() {
        return getCiblesAccessibles(getRange(), Utilitaire.animauxToEntites(getWorld().getAnimaux())).getFirst();
    }


    public int getIdEntite(){
        return id;
    }

    public int getIdPoids(){
        return idPoids;
    }





}
