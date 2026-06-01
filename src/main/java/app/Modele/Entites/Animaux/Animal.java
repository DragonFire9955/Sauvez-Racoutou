package app.Modele.Entites.Animaux;

import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Animal extends Entite {
    private double stunnedUntil[];
    private double[] slowUntil;
    private double vitesse;
    private boolean canAttack;
    private boolean allie;

    public Animal(double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(coord, health, coin, r, dmg, freqAtk, w);
        this.vitesse = vitesse;
        canAttack=true;
        stunnedUntil = new double[2];
        slowUntil = new double[3];
        this.allie=allie;
    }

    public Animal(double[] coord, double health, int coin, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(coord, health, coin, r, dmg, freqAtk, w);
        this.vitesse = 0;
        canAttack=true;
        stunnedUntil = new double[2];
        slowUntil = new double[3];
        this.allie=allie;
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

    public void deplacement() {
        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        if(vitesse == 0) return;
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



    protected Entite getNearest(){

        List<Entite> cibles = getListeCibles();

        if (cibles.isEmpty()) return null;

        Entite proche = cibles.getFirst();

        for(Entite a : cibles){
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
    protected List<Animal> getAnimauxCiblesAccessibles(double range, List<Animal> animaux){
        List<Animal> ciblesClassées = new ArrayList<>();
        int i;
        for(Animal a: animaux){
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



    public void attaquer(){

        if(!canAttack)return;

        List<Entite> cibles = getListeCibles();

        if(cibles.isEmpty()) return;

        Entite cible = cibles.getFirst();

        System.out.println("Animaux.attaquer(); " + this.getClass().getName() + " attaque !");
        cible.estAttaque(getDamage());
    }

    @Override
    public Entite getCible() {

        if (getListeCibles().isEmpty()) return null;

        if (allie)
            return getAnimauxCibles().getFirst();
        else {
            List<Animal> animaux = getWorld().getAllies();

            if(animaux.isEmpty()) {
                //System.out.println("anim empty");
                return null;
            }
            int i=0;
            while (i<animaux.size() && !(animaux.get(i) instanceof Racoutou))
                i++;
            if(i==animaux.size()){
                //System.out.println("pas racou");
                return null;
            }
            return animaux.get(i);
        }
    }

    public  List<Entite> getListeCibles(){
        List<Entite> listeCibles = new ArrayList<>();
        listeCibles.addAll(getAnimauxCibles());
        if(!this.allie){
            for(Barrage b: getWorld().getBarrage())
                listeCibles.add(b);
        }
        return listeCibles;
    }


    public  List<Animal> getAnimauxCibles(){
        List<Animal> listeCibles = new ArrayList<>();
        for(Animal a: getWorld().getAnimaux()){
            if(this.allie!=a.allie){
                listeCibles.add(a);
            }
        }
        return listeCibles;
    }

    public  List<Animal> getAnimauxCopains(){
        List<Animal> listeCibles = new ArrayList<>();
        for(Animal a: getWorld().getAnimaux()){
            if(this.allie==a.allie){
                listeCibles.add(a);
            }
        }
        return listeCibles;
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

    public boolean isAllie() {
        return allie;
    }

    public void setAllie(boolean allie) {
        this.allie = allie;
    }

}
