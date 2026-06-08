package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;

import java.util.List;

public class PouletIGPN extends Debuffer {

    public PouletIGPN(double[] coord, GameWorld w) {
        super("Poulet IGPN", coord, w, StatsEntiteInitialiser.getStatsLevels("Poulet IGPN"), true);
    }

    @Override
    public void debuff(double dt) {
        ///  TODO: ralentissement + force --
    }
}
