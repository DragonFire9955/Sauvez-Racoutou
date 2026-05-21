package app.Modele.Entites;

import app.Modele.CollisionUtil;
import app.Modele.GameWorld;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public abstract class Entite {

    private static int nbId =0;
    private int id;
    private GameWorld world;

    private DoubleProperty x, y;
    private double damage;
    private boolean alive;

    private DoubleProperty health = new SimpleDoubleProperty();
    private final double maxHealth;

    protected Entite(double x, double y, double health, double dmg, GameWorld w) {
        this.id=nbId;
        nbId++;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.health.set(health);
        this.maxHealth = health;
        this.damage=dmg;
        this.world=w;

        alive = true;
    }
    public void update(double dt){
        this.handleCollisions(getCible());
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }

    public void destroy() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }   //servira pr le clear

    public String getId() {
        return ""+id;
    }

    public GameWorld getWorld() {return world;}

    public DoubleProperty getHealthProperty() {
        return health;
    }

    public void setHealth(double value) {
        health.set(Math.max(0, value));
        if (value <= 0) destroy();
    }

    public double getMaxHealth() {
        return maxHealth;
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

    protected void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public boolean intersects(Entite b) {

        int RADIUS =32;
        double dx = (x.get()+RADIUS/2) - (b.getX()+RADIUS/2);
        double dy = (y.get()+RADIUS/2) - (b.getY()+RADIUS/2);
        return dx * dx + dy * dy < RADIUS * RADIUS;
    }

    public abstract Entite getCible();

    public void handleCollisions(Entite cible) {

        if(this.equals(cible) || cible==null) return;

        if (intersects(cible)){
            System.out.println("touche !");
            cible.setHealth(cible.getHealthProperty().getValue()-this.damage);
            this.destroy();
        }
    }



}
