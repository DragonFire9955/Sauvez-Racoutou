package app.Modele.Entites.Barrage;

import app.Modele.GameWorld;

import java.util.List;

public class Poubelle extends Barrage {
    private int poids;
    private int id;

    //LA TAILLE ETAIT A 10 ICI, 100 HEALTH, 5 COIN, 16 RANGE
    public Poubelle(double[] coord, GameWorld w, List<Object[]> statsLevels){
        super("Poubelle", coord, w, statsLevels);
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
