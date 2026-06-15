package app.Modele.Entites.Barrage;

import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;

import java.util.List;

public class Poubelle extends Barrage {


    //LA TAILLE ETAIT A 10 ICI, 100 HEALTH, 5 COIN, 16 RANGE
    public Poubelle(double[] coord, GameWorld w){
        super("poubelle", coord, w, StatsEntiteInitialiser.getStatsLevels("poubelle"), 1,  2);
    }



    @Override
    public void attaquer() {

    }

}
