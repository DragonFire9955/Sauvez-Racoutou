package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.BooleanProperty;

import java.util.List;
import java.util.Objects;

public abstract class Debuffer extends Specialise {

    private double vInitial;
    
    private int nbVictimes;

    private boolean chronoDefini;


    public Debuffer(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {

        super(name, coord, w, statsLevels, allie);

        nbVictimes = (int) statsLevels.get(0)[10];

        vInitial=super.getVitesse();

        chronoDefini=false;
    }

    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        debuff(dt);
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.nbVictimes = ((int) getStatsLevels().get(actualLevel)[10]);
    }

    public abstract void debuff(double dt);

    protected double getvInitial() {
        return vInitial;
    }
    protected void setvInitial(double vInitial) {
        this.vInitial = vInitial;
    }

    protected int getNbVictimes() {
        return nbVictimes;
    }
    protected void setNbVictimes(int nbVictimes) {
        this.nbVictimes = nbVictimes;
    }

    protected boolean isChronoDefini() {
        return chronoDefini;
    }

    protected void setChronoDefini(boolean chronoDefini) {
        this.chronoDefini = chronoDefini;
    }

    public  List<Animal> getListeCibles(){
        return Utilitaire.entitesToAnimaux(getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCibles())));
    };
}
