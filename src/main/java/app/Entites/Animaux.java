package app.Entites;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public abstract class Animaux {

    private double x, y;

    private boolean alive;

    private DoubleProperty health = new SimpleDoubleProperty();
    private final double maxHealth;

    private double vitesse;
    private double damage;
    private double range;

    public Animaux(double x, double y, double health, double vitesse, double dmg, double range) {
        this.x = x;
        this.y = y;
        this.health.set(health);
        this.maxHealth = health;
        this.vitesse = vitesse;
        this.damage = dmg;
        this.range = range;

        alive = true;
    }

    public abstract void update(double dt);    //Action des tours

    public void takeDamage(double dmg) {
        setHealth(getHealthProperty().doubleValue() - dmg);
    }

    public void destroy() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }   //servira pr le clear

    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    protected void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public double getVitesse() {
        return vitesse;
    }

    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
    }

    public DoubleProperty getHealthProperty() {
        return health;
    }
    public void setHealth(double value) {
        health.set(value);
        if (value <= 0) destroy();
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }
}
