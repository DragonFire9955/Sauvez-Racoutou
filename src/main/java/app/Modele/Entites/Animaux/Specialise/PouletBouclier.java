package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PouletBouclier extends Animal {

    private DoubleProperty bouclier;

    public PouletBouclier(GameWorld w) {

        super("Poulet bouclier", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Poulet bouclier"), false);

        this.bouclier = new SimpleDoubleProperty((double) StatsEntiteInitialiser.getStatsLevels("Poulet bouclier").get(0)[7]);
    }

    @Override
    public void update(double dt)  {

        super.update(dt);
        tankDmg();
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.bouclier.setValue(((double) getStatsLevels().get(actualLevel)[7]));
    }

    public void tankDmg() {

        double pvManquant = getMaxHealth() - getHealthProperty().getValue();

        if (bouclier.getValue() > 0) {

            if (bouclier.getValue() >= pvManquant) {
                //Le shield a pu tt absorber
                bouclier.setValue(bouclier.getValue() - pvManquant);
                getHealthProperty().setValue(getMaxHealth());
            } else {
                //Le shield a pas pu tt absorber dcp c ce qu'il reste
                double restant = pvManquant - bouclier.getValue();

                bouclier.setValue(0);
                getHealthProperty().setValue(getHealthProperty().getValue() + bouclier.getValue() + (pvManquant - restant));

                //Maj de la vie
                getHealthProperty().setValue(getHealthProperty().getValue() + (pvManquant - bouclier.getValue()));
            }

            if (bouclier.getValue() <= 0) {
                System.out.println("shield destroyed");
            }

        }
    }
}
