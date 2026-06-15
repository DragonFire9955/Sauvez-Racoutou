package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Diminue la force et la vitesse des ennemis quand ils se trouvent dans son périmètre d'action
 * Agit pendant un certain temps
 */

public class PouletIGPN extends Debuffer {
    private Map<Animal, Double[]> affectes;
    private double effetForce;
    private double effetVitesse;

    List<Entite> cibles;

    public PouletIGPN(double[] coord, GameWorld w) {
        super("pouletIGPN", coord, w, StatsEntiteInitialiser.getStatsLevels("pouletIGPN"), true);
        affectes = new HashMap<>();
        List<Object[]> statsLevels = StatsEntiteInitialiser.getStatsLevels("pouletIGPN");
        effetForce = (double) statsLevels.get(0)[11];
        effetVitesse = (double) statsLevels.get(0)[12];

        cibles = new ArrayList<>();
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.effetForce = ((int) getStatsLevels().get(actualLevel)[11]);
        this.effetVitesse = ((int) getStatsLevels().get(actualLevel)[12]);
    }

    @Override
    public void debuff(double dt) {
        cibles = getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCibles()));
        //Arreter l'effet quand sorti du perimètre
        for (Animal a: affectes.keySet()) {
            if(!cibles.contains(a)) {
                a.setDamage(affectes.get(a)[0]);
                a.setVitesse(affectes.get(a)[1]);
            }
        }
        affectes.keySet().removeIf(a -> !cibles.contains(a));


        if(!cibles.isEmpty()){
            if (isActionSpecialePossible()) {

                if (!isChronoDefini()) {
                    setChrono(dt);
                    setChronoDefini(true);
                }
                if (getChrono() + getTempsAction()> dt) {
                    Animal a;
                    for (int i = 0; i < cibles.size() && affectes.size()<= getNbVictimes(); i++) {
                        a = (Animal) cibles.get(i);
                        if(!affectes.containsKey(a)) {
                            affectes.put(a, new Double[]{a.getDamage(), a.getVitesse()});
                            a.setDamage(a.getDamage() * effetForce);
                            a.setVitesse(a.getVitesse() * effetVitesse);
                        }
                    }

                } else { //defiger bonhommes + this ne peut pas stun
                    for (Animal a: affectes.keySet()) {
                        if(!cibles.contains(a)) {
                            a.setDamage(affectes.get(a)[0]);
                            a.setVitesse(affectes.get(a)[1]);
                        }
                    }
                    affectes.keySet().removeIf(a -> !cibles.contains(a));
                    setActionSpecialePossible(false);
                }
            }

            if (isChronoDefini() && getChrono() + getTempsAction() + getTempsRepo() <= dt) { //this peut stun à nouveau
                setActionSpecialePossible(true);
                setChronoDefini(false);
            }
        }
    }

    @Override
    public boolean isAlive() {
        boolean isAlive = super.isAlive();

        if (!isAlive)
            for (Animal a: affectes.keySet()) {
                if(!cibles.contains(a)) {
                    a.setDamage(affectes.get(a)[0]);
                    a.setVitesse(affectes.get(a)[1]);
                }
            }
        affectes.keySet().removeIf(a -> !cibles.contains(a));

        return isAlive;
    }
}
