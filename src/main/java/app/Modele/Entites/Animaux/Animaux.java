package app.Modele.Entites.Animaux;

import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public abstract class Animaux extends Entite {

    private boolean canAttak;

    private double vitesse;

    public Animaux(double x, double y, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, List<Animaux> l) {
        super(x, y, health, r, dmg, freqAtk, w);
        this.vitesse = vitesse;
    }

    public void deplacement() {

        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        Animaux cible = this.getNearest();

        if(cible==null) return;

        double dx = cible.getX() - this.getX();
        double dy = cible.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {
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
        return canAttak;
    }
    public void setCanAttak(boolean canAttak) {
        this.canAttak = canAttak;
    }

    //FONCTIONS ATTAQUES

    /// TODO : elle ne fonctionne pas ducoup je l'ai enlevée pr l'instant on reviendras dessus mardi.
    //Retourne la liste des cibles ordonnées par distance croissante et pv croissant
    private List<Animaux> getCiblesAccessibles(){
        List<Animaux> ciblesAccessibles=getListeCibles();
        int i;
        for(Animaux a: ciblesAccessibles){
            //Si a dans le rayon d'action
            if(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())<=getRange()) {
                i = 0;
                //Tant que distance supérieur ET pv supérieur
                while(Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                        > Utilitaire.distance(this.getX(), this.getY(), ciblesAccessibles.get(i).getX(), ciblesAccessibles.get(i).getY())
                        && a.getHealthProperty().getValue()>ciblesAccessibles.get(i).getHealthProperty().getValue())
                    i++;
                ciblesAccessibles.add(i, a);
            }
        }
        return ciblesAccessibles;
    }

    public void estAttaque(double damage){

        setHealth(super.getHealthProperty().getValue()-damage);
    }

    @Override
    public void attaquer(){

        List<Animaux> cibles = getListeCibles();

        if(cibles.isEmpty()) return;

        Animaux cible = cibles.getFirst();

        cible.estAttaque(getDamage());
    }

    public abstract List<Animaux> getListeCibles();

}
