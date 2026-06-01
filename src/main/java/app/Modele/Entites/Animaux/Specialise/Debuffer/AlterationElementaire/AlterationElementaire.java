package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;


import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Debuffer;
import app.Modele.GameWorld;

import java.util.List;

//Altère les statistiques d'un ennemi qui serait rentrer dans son périmètre pendant un temps donné
public abstract class AlterationElementaire extends Debuffer {

    public AlterationElementaire(double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie, int nbV, double tDebuff, double tempsRecup, double rangeDebuff) {
        super(coord, health, coin, vitesse, r, dmg, freqAtk, w, allie, nbV, tDebuff, tempsRecup, rangeDebuff);
    }

    public void debuff(double dt, List<Animal> animaux){
        System.out.println(animaux.size()+" taille cibles");
        if (!animaux.isEmpty()) {

            if (isActionSpecialePossible()) {
                if (!isChronoDefini()) {
                    setChrono(dt);
                    setChronoDefini(true);
                }

                if (getChrono() == dt) {
                    for (int i = 0; i < animaux.size() && i < getNbVictimes(); i++) {
                        /*

                        cibles.get(i).setDamage(cibles.get(i).getDamage()/facteurDivForce);
                        cibles.get(i).setVitesse(cibles.get(i).getVitesse()/facteurDivVitesse);
                        cibles.get(i).setStunnedUntil(chrono + tempsSlow);
                        System.out.println(dt);
                        System.out.println("slow");

                         */
                        actionDebuff(animaux.get(i));
                    }

                } else if (getChrono() + getTempsAction()>= dt) { //defiger bonhommes + this ne peut pas stun
                    setActionSpecialePossible(false);
                    System.out.println("debut cooldown");
                }
                System.out.println(getChrono() + "chrono");
                System.out.println("dt " + dt);
            }

            if (isChronoDefini() && getChrono() + getTempsAction() + getTempsRepo() == dt) { //this peut stun à nouveau
                setActionSpecialePossible(true);
                setChronoDefini(false);
                System.out.println("fin cool down");
            }
        }
    }

    private List<Animal> listesCiblesNonAffectees(List<Animal> animaux){
        List<Animal> l= getAnimauxCiblesAccessibles(getRangeEffect(),animaux);
        for (int i=l.size() -1; i>=0; i--){
            if(l.get(i).getSlowUntil()[0]!=0)
                l.remove(i);
        }
        return l;
    }

    public abstract void actionDebuff(Animal a);


}
