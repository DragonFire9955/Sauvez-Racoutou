package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EntitesManager;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Animal extends Entite {
    private double[] stunnedUntil;
    private double[] slowUntil;
    private double vitesse;
    private boolean canAttack;
    private BooleanProperty allie;
    private DoubleProperty dirX;
    private BooleanProperty hypno;

    private int cibleInt;

    public Animal(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, int actualLevel, boolean allie) {
        super(name, coord, w, statsLevels, actualLevel);
        this.vitesse = (double) statsLevels.get(actualLevel)[6];
        canAttack=true;
        stunnedUntil = new double[2];
        slowUntil = new double[3];
        this.allie= new SimpleBooleanProperty(allie);
        dirX = new SimpleDoubleProperty(-1.);
        cibleInt = 2;
        hypno = new SimpleBooleanProperty(false);
    }

    @Override
    public void update(double dt)  {
        super.update(dt);

        if (!isColl() && vitesse!=0) {
            deplacement();
        }
        else if(stunnedUntil[0]!=0){
            unstuned(dt);
        }

        if (slowUntil[0]!=0){
            endSlow(dt);
        }
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        vitesse = ((double) getStatsLevels().get(actualLevel)[6]);
    }

    public void deplacement() {
        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile


        Entite cible = this.getDirection();

        if(cible==null) return;
        //if(cible instanceof Racoutou) AnimauxManager.deplacementEnnemi(this);
        if(cible instanceof Racoutou) EntitesManager.deplacementEnnemi2(this);
        else
            EntitesManager.deplacementAllie(this, (Animal) cible);

    }


    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public double getVitesse() {
        return vitesse;
    }

    public boolean canAttak() {
        return canAttack;
    }

    public void setCanAttack(boolean bool){
        canAttack=bool;
    }



    public void attaquer(){

        if(!canAttack)return;

        List<Entite> cibles = getEntitesCiblesNearest();

        if(cibles.isEmpty()) return;

        Entite cible = cibles.getFirst();

        cible.estAttaque(getDamage());
    }

    //Retourne l'entité vers laquelle this se dirige
    public Entite getDirection(){

        if(!allie.get())
            return getWorld().getRacoutou();
        else {
            if (getEntitesCiblesNearest().isEmpty()) return null;

            switch (cibleInt){
                case 0: //Strongest
                    return getAnimauxCiblesWeakest().getLast();
                case 1: //Weakest
                    return getAnimauxCiblesWeakest().getLast();
                case 2: //Nearest
                    return getEntitesCiblesNearest().getFirst();
                default:
                    return null;
            }
        }
    }

    @Override
    public Entite getCible(){

        if (getCiblesAccessibles(getRange(), getEntitesCiblesNearest()).isEmpty())
            return null;
        else
            return getCiblesAccessibles(getRange(), getEntitesCiblesNearest()).getFirst();
    }

    public  List<Animal> getAnimauxCibles(){
        if(allie.get())
            return getWorld().getEnnemis();
        else
            return getWorld().getAlliesAnimaux();
    }

    public List<Animal> getAnimauxCiblesWeakest(){
        List<Animal> animaux = new ArrayList<>();
        int i;
        for(Animal a: getAnimauxCibles()) {
            i= 0;
            //Tant que distance supérieur
            while (i < animaux.size()
                    && a.getHealthProperty().getValue()> getAnimauxCibles().get(i).getHealthProperty().getValue()) {
                i++;
            }
            animaux.add(i, a);
        }
        return animaux;
    }

    public List<Entite> getEntitesCiblesNearest(){
        List<Entite> entites;
        if(allie.get())
            entites = Utilitaire.animauxToEntites(getWorld().getEnnemis());
        else
            entites = this.getWorld().getAlliesAnimauxBarrages();

        List<Entite> entitesTriees = new ArrayList<>();
        int i;
        for(Entite e: entites) {
            i= 0;
            //Tant que distance supérieur
            while (i < entitesTriees.size()
                && Utilitaire.distance(this.getX(), this.getY(), e.getX(), e.getY())
                   > Utilitaire.distance(this.getX(), this.getY(), entitesTriees.get(i).getX(), entitesTriees.get(i).getY())) {
                i++;
            }
            entitesTriees.add(i, e);
        }
        return entitesTriees;
    }
    public List<Entite> getEntitesCiblesStrongest(){
        List<Entite> entites;
        if(allie.get())
            entites = Utilitaire.animauxToEntites(getWorld().getEnnemis());
        else
            entites = this.getWorld().getAlliesAnimauxBarrages();

        List<Entite> entitesTriees = new ArrayList<>();
        int i;
        for(Entite e: entites) {
            i= 0;
            //Tant que distance supérieur ET pv supérieur
            while (i < entitesTriees.size()
                    && Utilitaire.distance(this.getX(), this.getY(), e.getX(), e.getY())
                    > Utilitaire.distance(this.getX(), this.getY(), entitesTriees.get(i).getX(), entitesTriees.get(i).getY())) {
                i++;
            }
            entitesTriees.add(i, e);
        }
        return entitesTriees;
    }

    public  List<Animal> getAnimauxCopains(){
        List<Animal> animauxCopains = new ArrayList<>();
        if(allie.get())
            animauxCopains= getWorld().getAlliesAnimaux();
        else
            animauxCopains= getWorld().getEnnemis();
        for(int i = animauxCopains.size()-1; i>=0; i--){
            if(animauxCopains.get(i).getClass() == this.getClass())
                animauxCopains.remove(i);
        }

        return animauxCopains;
    }



    public void unstuned(double dt){
        if(dt>=this.stunnedUntil[0]) {

            this.vitesse=stunnedUntil[1];
            stunnedUntil[1]=0;
            this.setCanAttack(true);
            this.stunnedUntil[0]=0;
        }
    }

    public double[] getStunnedUntil() {
        return stunnedUntil;
    }

    public void setStunnedUntil(double[] stunnedUntil) {
        this.stunnedUntil[0]=stunnedUntil[0];
        this.stunnedUntil[1]=stunnedUntil[1];
    }

    public void setSlowUntil(double slow, double v, double f){
        this.slowUntil= new double[]{slow, v, f};
    }

    public double[] getSlowUntil() {
        return slowUntil;
    }

    public void endSlow(double dt){
        if(dt>=this.slowUntil[0]) {
            
            this.setVitesse(slowUntil[1]);
            this.setDamage(slowUntil[2]);
            this.slowUntil[0]=0;
        }
    }

    public BooleanProperty getAllie(){
        return allie;
    }

    public boolean isAllie() {
        return allie.get();
    }

    public void setAllie(boolean allie) {
        this.allie.set(allie);
    }

    public void setCibleInt(int cibleInt) {
        this.cibleInt = cibleInt;
    }

    public void setDirX(double dirX) {
        this.dirX.set(dirX);
    }

    public void inverseDirX(){
        dirX.set(-dirX.getValue());
    }

    public DoubleProperty getDirXProperty() {
        return dirX;
    }

    public double getDirX(){
        return dirX.getValue();
    }

    public BooleanProperty getHypno(){
        return hypno;
    }

    public void setHypno(boolean hypno) {
        this.hypno.set(hypno);
    }
}
