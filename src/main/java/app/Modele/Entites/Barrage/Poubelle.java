package app.Modele.Entites.Barrage;

import app.Modele.GameWorld;

public class Poubelle extends Barrage {
    private int idPoids;
    private int id;

    //LA TAILLE ETAIT A 10 ICI, 100 HEALTH, 5 COIN, 16 RANGE
    public Poubelle(double[] coord, GameWorld w){
        super("poubelle", coord, w, 100.0, 5, 10.0);
        idPoids = 2;
        id = 100;
    }

    public int getIdEntite(){
        return id;
    }

    @Override
    public int getIdPoids(){
        return idPoids;
    }

    @Override
    public void attaquer() {

    }

}
