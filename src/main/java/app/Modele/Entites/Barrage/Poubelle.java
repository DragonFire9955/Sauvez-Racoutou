package app.Modele.Entites.Barrage;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.List;

public class Poubelle extends Barrage {
    private int poids = 1;
    private int id = 100;

    //LA TAILLE ETAIT A 10 ICI, 100 HEALTH, 5 COIN, 16 RANGE
    public Poubelle(double[] coord, GameWorld w, List<Object[]> statsLevels){
        super("Poubelle", coord, w, statsLevels);
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
