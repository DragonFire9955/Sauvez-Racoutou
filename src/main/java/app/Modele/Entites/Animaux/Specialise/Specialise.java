package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public abstract class Specialise extends Animal{

    private double rangeEffect;
    private double tempsAction;
    private double tempsRepo;
    private boolean actionSpecialePossible;
    private double chrono;

    public Specialise(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie){

        super(name, coord, w, statsLevels, allie);

        this.rangeEffect = (double) statsLevels.get(0)[7];
        this.tempsAction = (double) statsLevels.get(0)[8];
        this.tempsRepo = (double) statsLevels.get(0)[9];

        this.actionSpecialePossible=true;

        this.chrono=0;
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
        return actionSpecialePossible;
    }
    public void setActionSpecialePossible(boolean bool) {
        this.actionSpecialePossible = bool;
    }


    public double getChrono() {
        return chrono;
    }

    public void setChrono(double chrono) {
        this.chrono = chrono;
    }
}
