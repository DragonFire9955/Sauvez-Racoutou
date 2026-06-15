package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

import static java.lang.Thread.sleep;

public class Racoutou extends Animal {

    public Racoutou(GameWorld w, List<Object[]> statsLevels) {
        super("racoutou", new double[]{(double) (((w.getTailleTile() * w.getMap()[0].length)/2) + w.getTailleTile()/2), (double) (((w.getTailleTile()* (w.getMap().length)) /2)+ w.getTailleTile()/2)}, w, statsLevels,0, true);
    }

    public Racoutou(double[] coord, GameWorld w, List<Object[]> statsLevels) {
        super("racoutou", coord, w, statsLevels, 0, true);
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
