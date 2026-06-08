package app.Modele.Entites.Animaux.Volants;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;

import java.util.List;

public class Volant extends Animal {

    public Volant(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {
        super(name, coord, w, statsLevels, allie);
    }
}
