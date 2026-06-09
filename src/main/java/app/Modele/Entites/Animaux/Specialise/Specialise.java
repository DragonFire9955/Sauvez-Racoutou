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

    public Specialise(String name, double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie, double tempsAction, double tempsRepo, double rangeEffect){
        super(name, coord, health, coin, vitesse, r, dmg, freqAtk, w, allie);
        this.rangeEffect=rangeEffect;
        this.tempsAction=tempsAction;
        this.tempsRepo=tempsRepo;
        this.actionSpecialePossible= new SimpleBooleanProperty(true);
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
