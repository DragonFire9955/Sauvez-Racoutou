package app.Modele.Entites;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

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

    private IntegerProperty level;
    private List<Object[]> statsLevels;

    private DoubleProperty health;
    private DoubleProperty maxHealth;
    //Prix de l'amélioration
    private int coin;
    private BooleanProperty alive;
    private boolean actif;

    private double chrono;


    protected Entite(String name, double coord[], GameWorld w, List<Object[]> statsLevels, int actualLevel) {
        this.level = new SimpleIntegerProperty(actualLevel);
        this.statsLevels = statsLevels;

        this.id=nbId;
        nbId++;

        coll = false;

        this.name = name;

        this.x = new SimpleDoubleProperty(coord[0]);
        this.y = new SimpleDoubleProperty(coord[1]);
        this.health = new SimpleDoubleProperty((double) statsLevels.get(actualLevel)[2]);
        this.maxHealth = new SimpleDoubleProperty(health.getValue());
        if (statsLevels.get(actualLevel)[1] != null) this.coin = (int) statsLevels.get(actualLevel)[1];
        this.range = (double) statsLevels.get(actualLevel)[3];
        this.damage = (double) statsLevels.get(actualLevel)[4];
        this.freqAtk = (double) statsLevels.get(actualLevel)[5];
        this.world=w;


        alive = new SimpleBooleanProperty(true);
        actif=true;
        chrono =0;
        System.out.println(actualLevel);
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
            if(chrono ==0)
                chrono =dt;

            coll = true;

            if ((dt- chrono) >= freqAtk) {
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

    public List<Object[]> getStatsLevels() {
        return statsLevels;
    }

    public DoubleProperty getHealthProperty() {
        return health;
    }

    public DoubleProperty getHealthMaxProperty(){
        return maxHealth;
    }
    public double getMaxHealth() {
        return maxHealth.get();
    }
    public void setHealth(double value) {
        if(value>maxHealth.getValue())
            health.set(maxHealth.getValue());
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

    //public abstract Entite getDirection();

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

    public void setStats(int actualLevel) {
        if(actualLevel<3)
            this.coin = ((int) statsLevels.get(actualLevel)[1]);
        //Calcul des pv par pourcentage
        this.health.setValue((double) statsLevels.get(actualLevel)[2] * (health.getValue()*100/maxHealth.getValue()));
        maxHealth.setValue(this.health.get());
        this.range = ((double) statsLevels.get(actualLevel)[3]);
        this.damage = ((double) statsLevels.get(actualLevel)[4]);
        this.freqAtk = ((double) statsLevels.get(actualLevel)[5]);
    }


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
            if(e.equals(this)) continue;

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

        return ciblesClassees;
    }

    public String getName() {
        return name;
    }

    public IntegerProperty getLevelProperty(){
        return level;
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public void incrementerLevel(){

        if(getLevel()<3){
            setLevel(getLevel()+1);
            world.setTotalCoin(world.getTotalCoin().get() - coin);
            setStats(getLevel());
        }

    };

}
