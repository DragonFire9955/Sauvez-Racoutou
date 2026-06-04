package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PouletBouclier extends Animal {

    private DoubleProperty bouclier;

    public PouletBouclier(double[] coord, GameWorld w) {

        super(coord, 6, 2, 1, 5, 2, 2, w, false);
        this.bouclier = new SimpleDoubleProperty(15);
    }

    @Override
    public void update(double dt)  {

        super.update(dt);
        tankDmg();
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
