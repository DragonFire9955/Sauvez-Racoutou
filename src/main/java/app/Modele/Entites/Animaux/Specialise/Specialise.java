package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public abstract class Specialise extends Animal{

    private double rangeEffect;
    private double tempsAction;
    private double tempsRepo;
    private BooleanProperty actionSpecialePossible;
    private double chrono;

    public Specialise(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, int actualLevel, boolean allie) {

        super(name, coord, w, statsLevels, actualLevel, allie);

        this.rangeEffect = (double) statsLevels.get(actualLevel)[7];
        this.tempsAction = (double) statsLevels.get(actualLevel)[8];
        this.tempsRepo = (double) statsLevels.get(actualLevel)[9];

        this.actionSpecialePossible = new SimpleBooleanProperty(true);
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.rangeEffect = ((double) getStatsLevels().get(actualLevel)[7]);
        this.tempsAction = ((double) getStatsLevels().get(actualLevel)[8]);
        this.tempsRepo = ((double) getStatsLevels().get(actualLevel)[9]);
    }

    public double getRangeEffect() {
        return rangeEffect;
    }
    public void setRangeEffect(double rangeEffect) {
        this.rangeEffect = rangeEffect;
    }

    public double getTempsAction() {
        return tempsAction;
    }
    public void setTempsAction(double tempsAction) {
        this.tempsAction = tempsAction;
    }

    public double getTempsRepo() {
        return tempsRepo;
    }
    public void setTempsRepo(double tempsRepo) {
        this.tempsRepo = tempsRepo;
    }

    public boolean isActionSpecialePossible() {
        return actionSpecialePossible.get();
    }
    public void setActionSpecialePossible(boolean bool) {
        this.actionSpecialePossible.set(bool);
    }
    public BooleanProperty getActionSpecialePossible(){
        return actionSpecialePossible;
    }


    public double getChrono() {
        return chrono;
    }

    public void setChrono(double chrono) {
        this.chrono = chrono;
    }
}
