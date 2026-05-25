package app.Modele.Entites;

import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entite {

    private static int nbId =0;
    private int id;
    private GameWorld world;

    private boolean coll;

    private DoubleProperty x, y;
    private double range;
    private double damage;
    private double freqAtk;

    private DoubleProperty health = new SimpleDoubleProperty();
    private final double maxHealth;
    private boolean alive;

    protected Entite(double x, double y, double health, double range, double dmg, double freqAtk, GameWorld w) {
        this.id=nbId;
        nbId++;

        coll = false;

        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.health.set(health);
        this.maxHealth = health;
        this.range = range;
        this.damage=dmg;
        this.freqAtk = freqAtk;
        this.world=w;

        alive = true;
    }

    public void update(double dt){
        this.handleCollisions(getCible(), dt);
    }

    public void handleCollisions(Entite cible, double dt) {

        if(this.equals(cible) || cible==null) return;

        if (Utilitaire.intersects(cible, this)){

            coll = true;

            if (dt%(getFreqAtk()*60) == 0) {
                System.out.println("attaque !");
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
        if (value <= 0) destroy();
    }

    public void destroy() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }   //servira pr le clear


    public abstract Entite getCible();

}
