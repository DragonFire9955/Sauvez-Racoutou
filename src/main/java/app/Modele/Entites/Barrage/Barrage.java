package app.Modele.Entites.Barrage;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public abstract class Barrage extends Entite {

    int[] tuileOrigine;
    public int taille;
    private int idPoids;

    public Barrage(String name, double[] coord, GameWorld w, int actualLevel){

        super(name, coord, w, StatsEntiteInitialiser.getStatsLevels(name), actualLevel);

        tuileOrigine = getTile();

        this.taille = (int) StatsEntiteInitialiser.getStatsLevels(name).get(actualLevel)[6];
        this.idPoids = (int) StatsEntiteInitialiser.getStatsLevels(name).get(actualLevel)[7];
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

    @Override
    public boolean incrementLevelPossible() {
        if(super.incrementLevelPossible()){
            int diff= (int) getStatsLevels().get(getLevel()+1)[6] - taille;
            if(diff==0) {
                return true;
            }
            else{
                boolean possible = true;
                int ligne = getTile()[0];
                int col = getTile()[1];
                int i = 0, j;
                while(possible && (ligne - i) <getWorld().getMap().length && i<=diff){
                    j = 0;
                    while (possible && (col - j) < getWorld().getMap()[ligne - i].length && j<=diff) {

                        if (i== 0 && j == 0)
                            j++;
                        else if (getWorld().getMap()[ligne - i][col - j] > 1) {
                            possible = false;
                        }
                        else
                            j++;
                    }
                    if(possible)
                        i++;
                }

                return possible;
            }

        }
        else
            return false;
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.taille = (int) StatsEntiteInitialiser.getStatsLevels(getName()).get(actualLevel)[6];
        this.idPoids = (int) StatsEntiteInitialiser.getStatsLevels(getName()).get(actualLevel)[7];

    }

    @Override
    public void incrementerLevel() {
        super.incrementerLevel();

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                getWorld().getMap()[tuileOrigine[0]-i][tuileOrigine[1]-j] = getIdPoids();
            }
        }
        if(taille > (int) StatsEntiteInitialiser.getStatsLevels(getName()).get(getLevel()-1)[6]) {
            setX(getX() - (double) getWorld().getTailleTile() / 2);
            setY(getY() - (double) getWorld().getTailleTile() / 2);
        }

        getWorld().resetDijkstraRacoutou();
    }
}
