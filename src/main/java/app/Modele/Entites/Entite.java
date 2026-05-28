package app.Modele.Entites;

import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import static java.lang.Thread.sleep;

public abstract class Entite {

    private static int nbId =0;
    private int id;
    private GameWorld world;

    private boolean coll; // indique si l'entite est en collision

    private DoubleProperty x, y;
    private double range;
    private double damage;
    private double freqAtk;

    private DoubleProperty health = new SimpleDoubleProperty();
    private final double maxHealth;
    private BooleanProperty alive;
    private boolean actif;

    private double chrono;


    protected Entite(double coord[], double health, double range, double dmg, double freqAtk, GameWorld w) {
        this.id=nbId;
        nbId++;

        coll = false;

        this.x = new SimpleDoubleProperty(coord[0]);
        this.y = new SimpleDoubleProperty(coord[1]);
        this.health.set(health);
        this.maxHealth = health;
        this.range = range;
        this.damage=dmg;
        this.freqAtk=freqAtk;
        this.world=w;


        alive = new SimpleBooleanProperty(true);
        actif=true;
        chrono=0;
    }
    public void update(double dt)  {
        this.handleCollisions(getCible(), dt);
    }

    public void handleCollisions(Entite cible, double dt) {

        if(this.equals(cible) || cible==null) return;

        if (Utilitaire.intersects(cible, this)){

            if(chrono==0)
                chrono=dt;

            coll = true;

            if (((dt-chrono)%freqAtk)== 0) {
                attaquer();
            }

        } else {
            coll = false;
        }

    }

    protected void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public abstract void attaquer();

    public String getId() {
        return ""+id;
    }

    public GameWorld getWorld() {return world;}

    public boolean isColl() {
        return coll;
    }

    public DoubleProperty getXProperty() {
        return x;
    }
    public DoubleProperty getYProperty() {
        return y;
    }
    public double getX() { return x.getValue(); }
    public double getY() { return y.getValue(); }
    public void setX(double x) {
        this.x.setValue(x);
    }
    public void setY(double y) {
        this.y.setValue(y);
    }

    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }

    public double getFreqAtk() {
        return freqAtk;
    }
    public void setFreqAtk(double freqAtk) {
        this.freqAtk = freqAtk;
    }


    public DoubleProperty getHealthProperty() {
        return health;
    }
    public double getMaxHealth() {
        return maxHealth;
    }
    public void setHealth(double value) {
        health.set(Math.max(0, value));
        System.out.println("avant destroy");
        if (value==0){
            destroy();
            System.out.println("après destroy");
        }
    }


    public void destroy() {
        alive.set(false);
    }

    public boolean isAlive() {
        return alive.getValue();
    }   //servira pr le clear

    public BooleanProperty getAliveProperty() {
        return alive;
    }

    public abstract Entite getCible();

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void estAttaque(double damage){

        setHealth(getHealthProperty().getValue()-damage);
    }









}
