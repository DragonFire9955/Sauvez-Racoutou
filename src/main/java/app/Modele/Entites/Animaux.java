package app.Modele.Entites;

import app.Modele.GameWorld;
import app.Modele.Utilitaire;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Animaux {

    private static int nbId =0;
    private int id;
    private GameWorld world;

    private List<Animaux> listeCibles;

    private DoubleProperty x, y;


    private boolean alive;

    private DoubleProperty health = new SimpleDoubleProperty();
    private final double maxHealth;


    private double vitesse;
    private double damage;
    private double range;

    public Animaux(double x, double y, double health, double vitesse, double dmg, double range, GameWorld w, List<Animaux> l) {
        this.id=nbId;
        nbId++;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.health.set(health);
        this.maxHealth = health;
        this.vitesse = vitesse;
        this.damage = dmg;
        this.range = range;
        this.world=w;
        this.listeCibles=l;

        alive = true;
    }
    //Appelle les fonctions d'actions de l'animal tous les dt (fréquence d'action)
    public abstract void update(double dt);
    //attaque


    public void destroy() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }   //servira pr le clear

    public String getId() {
        return ""+id;
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

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }


    public void deplacement() {

        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        Animaux cible = this.getNearest();

        if(cible==null) return;

        double dx = cible.getX() - this.getX();
        double dy = cible.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {return;}

        dx /= dist;
        dy /= dist;

        this.setX(this.getX() + dx * this.getVitesse());
        this.setY(this.getY() + dy * this.getVitesse());
    }



    private Animaux getNearest(){

        if (listeCibles.isEmpty()) return null;

        Animaux proche= listeCibles.get(0);

        for(Animaux a:this.listeCibles){
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                    < Utilitaire.distance(this.getX(), this.getY(), proche.getX(), proche.getY()))
                proche = a;
        }

        return proche;
    }

    protected void setListeCibles(List<Animaux> l){ this.listeCibles=l;}



//FONCTIONS ATTAQUES

    //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    private List<Animaux> getCiblesAccessibles(){
        List<Animaux> ciblesAccessibles=new ArrayList<>();
        int i;
        for(Animaux a: this.listeCibles){
            //Si a dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())<=range) {
                i = 0;
                //Tant que distance supérieur ET pv supérieur
                while(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                        > Utilitaire.distance(this.getX(), this.getY(), listeCibles.get(i).getX(), listeCibles.get(i).getY())
                        && a.getHealthProperty().getValue()>listeCibles.get(i).getHealthProperty().getValue())
                    i++;
                ciblesAccessibles.add(i, a);
            }
        }
        return ciblesAccessibles;
    }

    public void estAttaque(double damage){
        /*if(health.getValue()-damage>0)
            health.set(health.getValue()-damage);
        else {
            health.set(0);
            this.destroy();
        }*/
        setDamage(health.getValue()-damage);
    }
    public void attaque(){
        if(!this.getCiblesAccessibles().isEmpty())
            this.getCiblesAccessibles().get(0).estAttaque(this.damage);
    }

    public List<Animaux> getListeCibles(){
        return this.listeCibles;
    }
}
