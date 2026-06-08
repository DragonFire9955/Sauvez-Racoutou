package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

import static java.lang.Thread.sleep;

public class Racoutou extends Animal {

    public Racoutou(GameWorld w) {
        super("Racoutou", new double[]{(double) (((w.getTailleTile()/2 * w.getMap()[0].length)/2) + w.getTailleTile()/4), (double) (((w.getTailleTile()/2* (w.getMap().length)) /2)+ w.getTailleTile()/4)}, 50, 0, 0, 7, 10, .5, w, true);
    }

    public Racoutou(double[] coord, GameWorld w) {
        super("Racoutou", coord, 50, 0,  0, 7, 5, 2.5, w, true);
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
