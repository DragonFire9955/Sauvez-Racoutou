package app.Modele.Entites.Animaux;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public abstract class Animaux extends Entite {

    private boolean canAttak;

    private List<Animaux> listeCibles;

    private double vitesse;

    public Animaux(double x, double y, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, List<Animaux> l) {
        super(x, y, health, r, dmg, freqAtk, w);
        this.vitesse = vitesse;
        this.listeCibles=l;
    }

    public void deplacement() {

        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        Animaux cible = this.getNearest();

        if(cible==null) return;

        double dx = cible.getX() - this.getX();
        double dy = cible.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 5) {
            canAttak = true;
            return;
        } else {
            canAttak = false;
        }

        dx /= dist;
        dy /= dist;

        this.setX(this.getX() + dx * this.getVitesse());
        this.setY(this.getY() + dy * this.getVitesse());
    }



    private Animaux getNearest(){

        if (listeCibles.isEmpty()) return null;

        Animaux proche= listeCibles.getFirst();

        for(Animaux a:this.listeCibles){
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                    < Utilitaire.distance(this.getX(), this.getY(), proche.getX(), proche.getY()))
                proche = a;
        }

        return proche;
    }

    protected void setListeCibles(List<Animaux> l){ this.listeCibles=l;}

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public double getVitesse() {
        return vitesse;
    }

    public boolean canAttak() {
        return canAttak;
    }

    //FONCTIONS ATTAQUES

    //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    private List<Animaux> getCiblesAccessibles(){
        List<Animaux> ciblesAccessibles=new ArrayList<>();
        int i;
        for(Animaux a: this.listeCibles){
            //Si a dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())<=getRange()) {
                System.out.println("c");
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

        setHealth(super.getHealthProperty().getValue()-damage);

    }

    public void attaquer(){

        if(this.getCiblesAccessibles().isEmpty()) return;

        this.getCiblesAccessibles().getFirst().estAttaque(getDamage());
        System.out.println( this.getCiblesAccessibles().getFirst());
    }

    public List<Animaux> getListeCibles(){
        return this.listeCibles;
    }




}
