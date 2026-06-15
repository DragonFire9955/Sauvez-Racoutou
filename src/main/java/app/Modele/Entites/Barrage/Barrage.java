package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public abstract class Barrage extends Entite {
    public int taille;
    private int idPoids;

    public Barrage(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, int taille, int idPoids){

        super(name, coord, w, statsLevels);

        this.taille = taille;
        this.idPoids = idPoids;


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

    public int getIdPoids(){
        return idPoids;
    }





}
