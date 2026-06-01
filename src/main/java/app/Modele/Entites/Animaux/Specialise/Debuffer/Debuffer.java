package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.GameWorld;

import java.util.List;

public abstract class Debuffer extends Specialise {

    private double vInitial;
    
    private int nbVictimes;

    private boolean chronoDefini;
    private double chrono;


    public Debuffer(double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie,
                    int nbV, double tDebuff, double tempsRecup, double rangeDebuff) {
        super(coord, health, coin, vitesse, r, dmg, freqAtk, w, allie, rangeDebuff, tDebuff, tempsRecup);

        vInitial=super.getVitesse();
        
        nbVictimes=nbV;
        chronoDefini=false;
        chrono=0;
    }

    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        debuff(dt, getListeAnimaux());
    }

    public abstract void debuff(double dt, List<Animal> animaux);

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

    protected double getChrono() {
        return chrono;
    }
    protected void setChrono(double chrono) {
        this.chrono = chrono;
    }

    protected boolean isChronoDefini() {
        return chronoDefini;
    }
    protected void setChronoDefini(boolean chronoDefini) {
        this.chronoDefini = chronoDefini;
    }
}
