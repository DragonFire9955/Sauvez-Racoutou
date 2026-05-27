package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Chemins.DeplacementMethodes;
import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.Utilitaire;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

public class PouletClassique extends Animaux {
    public PouletClassique(GameWorld w) {
        super(new double[]{400, 200}, 5, 1, 5, 1, 1, w);
    }

    public PouletClassique(double[] coord, GameWorld w) {
        super(coord, 5, 1, 5, 1, 1, w);
    }

    public PouletClassique(double[] coord, double health, double vitesse, double range, double dmg, double freqAtk, GameWorld w) {
        super(coord, health, vitesse, range, dmg, freqAtk, w);
    }

    @Override
    public void update(double dt)  {
        super.update(dt);
        if (!isColl() && canMove()) {
            deplacement();
        }
    }

    @Override
    public Entite getCible() {

        List<Animaux> allies= getWorld().getAllies();
        int i=0;
        while(i<allies.size() && !(allies.get(i) instanceof Racoutou))
            i++;

        if(i==allies.size()) {
            getWorld().changeStateTheEnd();
            return null;
        }
        return allies.get(i);
    }

    @Override
    public List<Animaux> getListeCibles() {
        return getWorld().getAllies();
    }
}
