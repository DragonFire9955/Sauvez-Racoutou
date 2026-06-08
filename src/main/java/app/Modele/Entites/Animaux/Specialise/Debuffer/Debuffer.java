package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;
import java.util.Objects;

public abstract class Debuffer extends Specialise {

    private double rangeDebuff;
    private double tDebuff;
    private double tempsRecup;

    private double vInitial;
    
    private int nbVictimes;

    private boolean chronoDefini;
    private double chrono;


    public Debuffer(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {

        super(name, coord, w, statsLevels, allie);

        this.rangeDebuff = (double) statsLevels.get(0)[7];
        this.tDebuff = (double) statsLevels.get(0)[8];
        this.tempsRecup = (double) statsLevels.get(0)[9];
        nbVictimes = (int) statsLevels.get(0)[10];

        vInitial=super.getVitesse();

        chronoDefini=false;
        chrono=0;
    }

    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        debuff(dt);
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
