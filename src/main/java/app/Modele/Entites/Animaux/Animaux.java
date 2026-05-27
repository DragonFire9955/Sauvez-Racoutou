package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class Animaux extends Entite {
    private double stunnedUntil;
    private double[] slowUntil;


    private boolean canAttack;
    private boolean canMove;
    private double vitesse;

    public Animaux(double[] coord, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w) {
        super(coord, health, r, dmg, freqAtk, w);
        this.vitesse = vitesse;
        canAttack=true;
        canMove=true;
        stunnedUntil =0;
        slowUntil = new double[3];
    }

    @Override
    public void update(double dt)  {
        super.update(dt);

        if (!isColl() && canMove()) {
            deplacement();
        }
        else if(stunnedUntil!=0){
                unstuned(dt);
        }

        if (slowUntil[0]!=0){
            endSlow(dt);
        }

    }

    public void deplacement() {
        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        if(!canMove) return;
        Entite cible = this.getCible();

        if(cible==null) return;

        double dx = cible.getX() - this.getX();
        double dy = cible.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) { return;}

        dx /= dist;
        dy /= dist;

        this.setX(this.getX() + dx * this.getVitesse());
        this.setY(this.getY() + dy * this.getVitesse());
    }



    protected Animaux getNearest(){

        List<Animaux> cibles = getListeCibles();

        if (cibles.isEmpty()) return null;

        Animaux proche = cibles.getFirst();

        for(Animaux a : cibles){
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                    < Utilitaire.distance(this.getX(), this.getY(), proche.getX(), proche.getY()))
                proche = a;
        }

        return proche;
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


    //FONCTIONS ATTAQUES

    //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    protected List<Animaux> getCiblesAccessibles(double range){
        List<Animaux> ciblesAccessibles=getListeCibles();
        List<Animaux> ciblesClassées = new ArrayList<>();
        int i;
        for(Animaux a: ciblesAccessibles){
            //Si a dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())<=range) {
                i = 0;
                //Tant que distance supérieur ET pv supérieur
                while( i<ciblesClassées.size() &&
                        Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                        > Utilitaire.distance(this.getX(), this.getY(), ciblesClassées.get(i).getX(), ciblesClassées.get(i).getY())
                        && a.getHealthProperty().getValue()>ciblesClassées.get(i).getHealthProperty().getValue()){
                        i++;
                    //System.out.println("ajout "+ a.getClass() +" indice "+i);
                }
                ciblesClassées.add(i, a);
            }
        }
        return ciblesClassées;
    }

    public void estAttaque(double damage){

        setHealth(super.getHealthProperty().getValue()-damage);
    }

    public void attaquer(){
        System.out.println(this.getClass().getName()+" can attack="+ canAttack);
        if(!canAttack)return;

        List<Animaux> cibles = getListeCibles();

        if(cibles.isEmpty()) return;

        Animaux cible = cibles.getFirst();

        System.out.println("Animaux.attaquer(); " + this.getClass().getName() + " attaque !");
        cible.estAttaque(getDamage());
    }

    public abstract List<Animaux> getListeCibles();

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    public boolean canMove() {
        return canMove;
    }

    public void unstuned(double dt){
        if(dt>=this.stunnedUntil) {
            System.out.println("if stunnedUntil");
            this.setCanMove(true);
            this.setCanAttack(true);
            this.stunnedUntil=0;
        }
    }

    public double getStunnedUntil() {
        return stunnedUntil;
    }

    public void setStunnedUntil(double stunnedFor) {
        this.stunnedUntil = stunnedFor;
    }

    public void setSlowUntil(double slow, double v, double f){
        this.slowUntil= new double[]{slow, v, f};
    }

    public double[] getSlowUntil() {
        return slowUntil;
    }

    public void endSlow(double dt){
        if(dt>=this.slowUntil[0]) {
            System.out.println("if slowUntill");
            this.setVitesse(slowUntil[1]);
            this.setDamage(slowUntil[2]);
            this.slowUntil[0]=0;
        }
    }





}
