package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Chemins.DeplacementMethodes;
import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;
import java.util.Map;

public class PouletClassique extends Animaux {
    public PouletClassique(GameWorld w) {
        super(400, 200, 6, 5, 1, 5, w, w.getAllies());
    }

    @Override
    public void update(double dt) {
        super.update(dt);
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
}
