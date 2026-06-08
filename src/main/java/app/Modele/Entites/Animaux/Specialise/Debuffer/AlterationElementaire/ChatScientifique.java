package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;

import java.util.List;

public class ChatScientifique extends AlterationElementaire {

    private double facteurDivVitesse;
    private double facteurDivForce;


    public ChatScientifique(double[] coord, GameWorld w) {

        super("Chat Scientifique", coord, w, StatsEntiteInitialiser.getStatsLevels("Chat scientifique"), true);

        List<Object[]> listStats = StatsEntiteInitialiser.getStatsLevels("Chat scientifique");

        facteurDivForce = (double) listStats.get(0)[11];
        facteurDivVitesse = (double) listStats.get(0)[10];
    }


    public void actionDebuff(Animal a, double dt) {
        a.setSlowUntil((getChrono() + getTempsAction()), a.getVitesse(), a.getDamage() );

        a.setDamage(a.getDamage()/facteurDivForce);
        a.setVitesse(a.getVitesse()/facteurDivVitesse);
        System.out.println();
        System.out.println("slow");
    }


    public List<Animal> getListeCibles() {
        return getWorld().getEnnemis();
    }

}
