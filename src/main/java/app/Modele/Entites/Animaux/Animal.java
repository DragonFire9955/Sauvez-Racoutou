package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Animal extends Entite {
    private double[] stunnedUntil;
    private double[] slowUntil;
    private double vitesse;
    private boolean canAttack;
    private boolean allie;

    public Animal(String name, double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie) {
        super(name, coord, health, coin, r, dmg, freqAtk, w);
        this.vitesse = vitesse;
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

        //if(vitesse == 0) return;

        Entite cible = this.getDirection();
        //System.out.println("entite cible de  "+this.getId() +" : "+ cible.getId());

        if(cible==null) return;
        //if(cible instanceof Racoutou) AnimauxManager.deplacementEnnemi(this);
        if(cible instanceof Racoutou) AnimauxManager.deplacementEnnemi2(this);

        else {
            double dx = cible.getX() - this.getX();
            double dy = cible.getY() - this.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < 2) {
                return;
            }

            dx /= dist;
            dy /= dist;

            this.setX(this.getX() + dx * this.getVitesse());
            this.setY(this.getY() + dy * this.getVitesse());
        }

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




    /*
        //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    protected List<Animal> getAnimauxCiblesAccessibles(double range, List<Animal> animaux){
        List<Animal> ciblesClassees = new ArrayList<>();
        int i;
        for(Animal a: animaux){
            //Si a dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())<=range) {
                i = 0;
                //Tant que distance supérieur ET pv supérieur
                while( i<ciblesClassees.size() &&
                        Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                        > Utilitaire.distance(this.getX(), this.getY(), ciblesClassees.get(i).getX(), ciblesClassees.get(i).getY())){
                        i++;
                        while(i<ciblesClassees.size() && a.getHealthProperty().getValue()>ciblesClassees.get(i).getHealthProperty().getValue()) {
                            i++;
                        }
                }
                ciblesClassees.add(i, a);
            }
        }
        if(ciblesClassees.size()>2) {
            for (int ind = 0; ind < ciblesClassees.size(); ind++) {
            }
        }
        return ciblesClassees;
    }
     */



    public void attaquer(){

        if(!canAttack)return;

        List<Entite> cibles = getEntitesCibles();

        if(cibles.isEmpty()) return;

        Entite cible = cibles.getFirst();

        cible.estAttaque(getDamage());
    }

    //Retourne l'entité vers laquelle this se dirige
    @Override
    public Entite getDirection(){

        if(!allie)
            return getWorld().getRacoutou();
        else
            if (getEntitesCibles().isEmpty())
                return null;
            else
                return getEntitesCibles().getFirst();
    }

    @Override
    public Entite getCible(){

        if (getCiblesAccessibles(getRange(), getEntitesCibles()).isEmpty())
            return null;
        else
            return getCiblesAccessibles(getRange(), getEntitesCibles()).getFirst();
    }

    public  List<Animal> getAnimauxCibles(){
        if(allie)
            return getWorld().getEnnemis();
        else
            return getWorld().getAlliesAnimaux();
    }

    public List<Entite> getEntitesCibles(){
        List<Entite> entites;
        if(allie)
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
        if(allie)
            return getWorld().getAlliesAnimaux();
        else
            return getWorld().getEnnemis();
    }

    public List<Animal> getAnimauxCopainsClasses(){

        List<Animal> entitesTriees = new ArrayList<>();
        List<Animal> copains = getAnimauxCopains();
        int i;
        for(Animal a: copains) {
            if(a.equals(this)) continue;
            i= 0;
            //Tant que distance supérieur ET pv supérieur
            while (i < entitesTriees.size()
                    && Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                    > Utilitaire.distance(this.getX(), this.getY(), entitesTriees.get(i).getX(), entitesTriees.get(i).getY())) {
                i++;
            }
            entitesTriees.add(i, a);
        }
        return entitesTriees;
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

    public int getPoids(){
        return 0;
    }



}
