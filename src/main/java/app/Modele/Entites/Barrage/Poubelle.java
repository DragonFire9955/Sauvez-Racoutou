package app.Modele.Entites.Barrage;

import app.Modele.GameWorld;
import app.Modele.Managers.EntitesManager;

public class Poubelle extends Barrage {


    //LA TAILLE ETAIT A 10 ICI, 100 HEALTH, 5 COIN, 16 RANGE
    public Poubelle(double[] coord, GameWorld w){
        super("poubelle", coord, w, EntitesManager.niveauDeBasesLorsAchat.get("poubelle"), 100.0, 5, 1, 2, 100);
    }



    @Override
    public void attaquer() {

    }

}
