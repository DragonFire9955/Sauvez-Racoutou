package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;

import java.util.List;

public class ChatScientifique extends AlterationElementaire {

    private double facteurDivVitesse;
    private double facteurDivForce;


    public ChatScientifique(double[] coord, GameWorld w, int actualLevel) {

        super("chatScientifique", coord, w, StatsEntiteInitialiser.getStatsLevels("chatScientifique"), actualLevel, true);

        List<Object[]> listStats = StatsEntiteInitialiser.getStatsLevels("chatScientifique");

        facteurDivForce = (double) listStats.getFirst()[11];
        facteurDivVitesse = (double) listStats.getFirst()[12];
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.facteurDivForce = ((double) getStatsLevels().get(actualLevel)[11]);
        this.facteurDivVitesse = ((double) getStatsLevels().get(actualLevel)[12]);
    }


    public void actionDebuff(Animal a, double dt) {
        a.setSlowUntil((getChrono() + getTempsAction()), a.getVitesse(), a.getDamage() );

        a.setDamage(a.getDamage()*facteurDivForce);
        a.setVitesse(a.getVitesse()*facteurDivVitesse);
    }


    public List<Animal> getListeCibles() {
        return getWorld().getEnnemis();
    }

}
