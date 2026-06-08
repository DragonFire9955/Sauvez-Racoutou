package app.Modele.Entites;

import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

public abstract class Entite {

    private String name;

    private static int nbId =0;
    private int id;
    private GameWorld world;

    private boolean coll; // indique si l'entite est en collision

    private DoubleProperty x, y;
    private double range;
    private double damage;
    private double freqAtk;

    private DoubleProperty health;
    private final double maxHealth;
    private int coin;
    private BooleanProperty alive;
    private boolean actif;

    private double chrono;

    private List<Object[]> statsLevels;


    protected Entite(String name, double coord[], double health, int coin, double range, double dmg, double freqAtk, GameWorld w) {

        this.id=nbId;
        nbId++;

        coll = false;

        this.name = name;

        this.x = new SimpleDoubleProperty(coord[0]);
        this.y = new SimpleDoubleProperty(coord[1]);
        this.health = new SimpleDoubleProperty(health);
        this.maxHealth = health;
        this.coin = coin;
        this.range = range;
        this.damage=dmg;
        this.freqAtk=freqAtk;
        this.world=w;


        alive = new SimpleBooleanProperty(true);
        actif=true;
        chrono=0;

        statsLevels = new ArrayList<>();
    }
    public void update(double dt)  {
        this.handleCollisions(getCible(), dt);
    }

    public void handleCollisions(Entite cible, double dt) {

        if(this.equals(cible) || cible==null) {

            coll = false;
            return;
        }

        if (Utilitaire.intersects(cible, this)){
            if(chrono==0)
                chrono=dt;

            coll = true;

            if (((dt-chrono)) >= freqAtk) {
                attaquer();
                chrono = 0;
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
        if(value>maxHealth)
            health.set(maxHealth);
        else {
            health.set(Math.max(0, value));
            if (health.getValue()== 0) {
                destroy();
            }
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

    public List<Object[]> getStatsLevels() {
        return statsLevels;
    }

    public void setStatsLevels(List<Object[]> statsLevels) {
        this.statsLevels = statsLevels;
    }

    public abstract Entite getDirection();

    public abstract Entite getCible();

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void estAttaque(double damage){

        setHealth(getHealthProperty().getValue()-damage);
    }

    public int getCoin() {
        return coin;
    }
    public void setCoin(int coin) {
        this.coin = coin;
    }
    public  int getIdEntite(){return id;}


    public int[] getTile(){
        int[] tile = new int[2];
        tile[0]= (int) (y.getValue()/ world.getTailleTile());
        tile[1]= (int) (x.getValue()/ world.getTailleTile());
        return tile;
        //return new int[]{Utilitaire.divisionEuclidienne(y.getValue(), world.getTailleTile()), Utilitaire.divisionEuclidienne(x.getValue(), world.getTailleTile())};
    }

    public double[] getCoord(){
        return new double[]{x.getValue(), y.getValue()};
    }

    //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    public List<Entite> getCiblesAccessibles(double range, List<Entite> entites){
        List<Entite> ciblesClassees = new ArrayList<>();
        int i;
        for(Entite e: entites){
            //Si e dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), e.getX(), e.getY())<=range) {
                i = 0;
                //Tant que distance supérieur ET pv supérieur
                while( i<ciblesClassees.size() &&
                        Utilitaire.distance(this.getX(), this.getY(), e.getX(), e.getY())
                                > Utilitaire.distance(this.getX(), this.getY(), ciblesClassees.get(i).getX(), ciblesClassees.get(i).getY())){
                    i++;
                    while(i<ciblesClassees.size() && e.getHealthProperty().getValue()>ciblesClassees.get(i).getHealthProperty().getValue()) {
                        i++;
                    }
                }
                ciblesClassees.add(i, e);
            }
        }
        if(ciblesClassees.size()>2) {
            for (int ind = 0; ind < ciblesClassees.size(); ind++) {
            }
        }
        return ciblesClassees;
    }

    public String getName() {
        return name;
    }
}
