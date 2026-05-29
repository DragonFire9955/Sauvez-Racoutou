package app.Modele.Entites.Animaux.ChienIntermittent;/*
    Faire une classe Canon qui crée un chien ?
    Comme ça le chien fait le déplacement aller retour avec par exemple un booléen ou un pivot de posDep/posArr
    Et le chien meurt dans le code comme ça plus simple : tant que canon n'a pas sa variable "Chien" vide, relance pas cdn atk.
 */


import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ChienIntermittent extends Animal {

    private Canon canon;

    private double chrono;
    private double cdnLaunch;

    private double[] posArr;

    private DoubleProperty sizeScale;
    private BooleanProperty heBelieveHeCanFly;

    //Pour la fonction versInfiniEtAuDela
    private double scaleMax = 2.0;
    private double scaleMin = 1.0;
    private double vitesseScale = .01;

    private boolean scaleMaxAtteint = false;
    private double distanceMaxScale = 0;

    public ChienIntermittent(double[] coord, GameWorld w, Canon canon) {

        super(coord, 4, 5, 30, 8, 10, w, true);

        this.canon=canon;

        this.chrono = 0;
        this.cdnLaunch = 5;

        posArr = new double[] {coord[0], coord[1]};

        sizeScale = new SimpleDoubleProperty(1);
        heBelieveHeCanFly.set(false);
    }

    @Override
    public void update(double dt) {

        if (chrono == 0) {

            chrono = dt;
            initChien();  //Crée ladite image, ou plutot un listener sur le booléen chienAtterit ?
        }

        if (chrono + cdnLaunch >= dt) {
            chrono = 0;
        } else {

            if (heBelieveHeCanFly.getValue() == true)
                deplacement();

            deplacement();  //Déplacement du chien vers le canon
        }
    }

    public void initChien() {

        Entite cible = getCible();

        if (cible==null) return;

        posArr = new double[] {cible.getX(), cible.getY()};
        heBelieveHeCanFly.setValue(true);
    }

    //A move dans la partie controller et bind
    public void modifSize() {
        double distanceTotale = Utilitaire.distance(canon.getX(), canon.getY(), posArr[0], posArr[1]);
        double distanceParcourue = Utilitaire.distance(canon.getX(), canon.getY(), this.getX(), this.getY());
        double scale = sizeScale.getValue();

        //Grossissement
        if (!scaleMaxAtteint) {

            scale += vitesseScale;

            if (scale >= scaleMax) {
                scale = scaleMax;

                //Garde distance où max atteint
                scaleMaxAtteint = true;
                distanceMaxScale = distanceParcourue;
            }
        }

        //Rapetessissement
        double distanceRestante = distanceTotale - distanceParcourue;

        if (scaleMaxAtteint && distanceRestante <= distanceMaxScale) {

            scale -= vitesseScale;

            if (scale <= scaleMin) {
                scale = scaleMin;
            }
        }

        sizeScale.setValue(scale);
    }
/*
    public void viensAuPied() {

        //tant qu'il n'y a pas de cible ou qu'il y a une cible dans le perimètre d'action: immobile
        if(getVitesse()==0) return;

        double dx = posDep[0] - this.getX();
        double dy = posDep[1] - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) { return;}

        dx /= dist;
        dy /= dist;

        this.setX(this.getX() + dx * this.getVitesse());
        this.setY(this.getY() + dy * this.getVitesse());
    }

 */
    @Override
    public Entite getCible(){
        return this.canon;
    }

    public Entite getPremierEnnemi(){
        return getAnimauxCiblesAccessibles(canon.getRange(), getWorld().getEnnemis()).getFirst();
    }

}