package app.Modele.Entites.Animaux.Specialise.Debuffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
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

    public PouletIGPN(double[] coord, GameWorld w) {
        super("Poulet IGPN", coord, 4, 1, 1, 2, 0.5, 1, w, true, 3, 5, 2, 500);
        affectes = new HashMap<>();
        effetForce = 0.8;
        effetVitesse = 0.8;
    }

    @Override
    public void debuff(double dt) {
        List<Entite> cibles = getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCibles()));
        System.out.println("size : "+ cibles.size());
        //Arreter l'effet quand sorti du perimètre
        for(Map.Entry<Animal, Double[]> entry: affectes.entrySet()){
            if(!cibles.contains(entry.getKey())) {
                entry.getKey().setDamage(entry.getValue()[0]);
                entry.getKey().setVitesse(entry.getValue()[1]);
                affectes.remove(entry.getKey());
                System.out.println("sort du perimètre");
            }
        }
        System.out.println(!cibles.isEmpty());
        if(!cibles.isEmpty()){
            System.out.println("actionSpe: " + isActionSpecialePossible());
            if (isActionSpecialePossible()) {

                if (!isChronoDefini()) {
                    setChrono(dt);
                    System.out.println("def chrono: "+ dt);
                    setChronoDefini(true);
                }
                System.out.println("condition entree: "+ (getChrono() + getTempsAction()> dt));
                if (getChrono() + getTempsAction()> dt) {
                    Animal a;
                    for (int i = 0; i < cibles.size() && affectes.size()<= getNbVictimes(); i++) {
                        a = (Animal) cibles.get(i);
                        System.out.println("affectes size: "+affectes.size());
                        if(!affectes.containsKey(a)) {
                            System.out.println("Avant: "+ a.getDamage()+", "+ a.getVitesse());
                            affectes.put(a, new Double[]{a.getDamage(), a.getVitesse()});
                            a.setDamage(a.getDamage() * effetForce);
                            a.setVitesse(a.getVitesse() * effetVitesse);
                            System.out.println("Après: "+ a.getDamage()+", "+ a.getVitesse());
                        }
                    }

                } else { //defiger bonhommes + this ne peut pas stun
                    for(Map.Entry<Animal, Double[]> entry: affectes.entrySet()){
                        entry.getKey().setDamage(entry.getValue()[0]);
                        entry.getKey().setVitesse(entry.getValue()[1]);
                        affectes.remove(entry.getKey());
                        System.out.println("fin igpn");
                    }
                    setActionSpecialePossible(false);
                }
            }

            if (isChronoDefini() && getChrono() + getTempsAction() + getTempsRepo() == dt) { //this peut stun à nouveau
                setActionSpecialePossible(true);
                setChronoDefini(false);
            }
        }

    }
}
