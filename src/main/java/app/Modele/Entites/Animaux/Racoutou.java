package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

import static java.lang.Thread.sleep;

public class Racoutou extends Animal {

    public Racoutou(GameWorld w) {
        super(new double[]{(double) ((w.getTailleTile() * (w.getMap()[0].length/2))+w.getTailleTile()/2), (double) ((w.getTailleTile() * (w.getMap().length /2))+ w.getTailleTile()/2)}, 500, 0, 0, 7, 10, .5, w, true);
    }

    public Racoutou(double[] coord, GameWorld w) {
        super(coord, 50, 0,  0, 7, 5, 2.5, w, true);
    }

    @Override
    public void update(double dt)  {
        super.update(dt);
    }

    public Entite getDirection(){

        List<Animal> cibles = Utilitaire.entitesToAnimaux(getCiblesAccessibles(this.getRange(), Utilitaire.animauxToEntites(getWorld().getEnnemis())));

        if (cibles.isEmpty()) return null;

        return cibles.getFirst();
    }



}
