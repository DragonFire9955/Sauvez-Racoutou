package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.HashMap;
import java.util.Map;

public class Ruchien extends Debuffer {

    private double cumulTemps = 0;
    private double tempsRequisPalier;
    private int angleActuel = 0; //de 0 à 3 pour savoir on va a quel angle
    private boolean premierDeplacement = true;

    private final Map<Animal, Double> valeurFixeBaisseAllies = new HashMap<>();

    public Ruchien(double[] coord, GameWorld w) {
        super("ruchien", coord, w, StatsEntiteInitialiser.getStatsLevels("ruchien"), false);

        Object[] stats = StatsEntiteInitialiser.getStatsLevels("ruchien").get(0);
        this.tempsRequisPalier = Double.parseDouble(stats[11].toString());

    }

    @Override
    public void debuff(double dt) {
        this.cumulTemps += dt;

        //le débuff se déclenche toutes les 5 sec
        if (this.cumulTemps >= (this.tempsRequisPalier * 1000)) {
            this.cumulTemps = 0;

            for (Animal a : getWorld().getAnimaux()) {
                if (a.isAllie() && !a.getName().equalsIgnoreCase("racoutou")) {

                    double degatsActuels = a.getDamage();
                    double seuilMinimum = 1.0;

                    //si l'allié est déjà au seuil minimum on passe au suivant
                    if (degatsActuels <= seuilMinimum) {
                        continue;
                    }

                    //si c'est la première fois que le ruchien debuff l'animal
                    if (!valeurFixeBaisseAllies.containsKey(a)) {
                        double dixPourcent = degatsActuels * 0.10;
                        valeurFixeBaisseAllies.put(a, dixPourcent);
                    }

                    double baisseAPliquer = valeurFixeBaisseAllies.get(a);
                    double nouveauxDegats = degatsActuels - baisseAPliquer;
                    //prend lz valeur de baisse et applique les nouveaux dégats

                    //pour ne pas dépasser le seuil inimum
                    if (nouveauxDegats < seuilMinimum) {
                        nouveauxDegats = seuilMinimum;
                    }

                    double degatsPerdus = degatsActuels - nouveauxDegats;

                    a.setDamage(nouveauxDegats);

                    //affichage que si les degats change
                    if (a.getDamage() < degatsActuels) {
                        String depart = String.valueOf(degatsActuels);
                        String perte = String.valueOf(degatsPerdus);
                        String fin = String.valueOf(a.getDamage());

                        System.out.println("Ruchien : " + a.getName()
                                + " part de : " + depart
                                + " degats et perd : -" + perte
                                + " puis arrive a : " + fin);
                    }
                }
            }
        }
    }

    @Override
    public void deplacement() {
        GameWorld w = this.getWorld();
        double marge = 20; //par rapport aux bord

        double longueur = w.getMap()[0].length * w.getTailleTile();
        double hauteur = w.getMap().length * w.getTailleTile();

        double[][] angles = {
                {marge, marge},                           // 0 : HG
                {longueur - marge, marge},              // 1 : HD
                {longueur - marge, hauteur - marge}, // 2 : BD
                {marge, hauteur - marge}               // 3 : BG
        };

        if (this.premierDeplacement) {
            double distanceMin = 10000.0;
            for (int i = 0; i < angles.length; i++) {
                double dist = Utilitaire.distance(angles[i][0], angles[i][1], this.getX(), this.getY());
                if (dist < distanceMin) {
                    distanceMin = dist;
                    this.angleActuel = i;
                }
            }
            this.premierDeplacement = false;
        }

        double[] cible = angles[this.angleActuel];
        double dist = Utilitaire.distance(cible[0], cible[1], this.getX(), this.getY());
        //distance entre le ruchien et l'angle

        if (dist < 5) { //si il est a moins de 5 pixels il est arrivé
            this.angleActuel = (this.angleActuel + 1) % 4; //pour le cas 3 ((3+1)%4 = 0)
        } else {
            double dx = cible[0] - this.getX();
            double dy = cible[1] - this.getY();

            //on divise l'écart par la distance pour avoir une direction entre -1 et 1
            dx = dx / dist;
            dy = dy / dist;

            this.setX(this.getX() + dx * this.getVitesse());
            this.setY(this.getY() + dy * this.getVitesse());
        }
    }
}