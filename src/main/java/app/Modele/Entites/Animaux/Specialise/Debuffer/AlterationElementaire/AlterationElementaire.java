package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;


import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Debuffer;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

//Altère les statistiques d'un ennemi qui serait rentrer dans son périmètre pendant un temps donné
public abstract class AlterationElementaire extends Debuffer {

    public AlterationElementaire(String name, double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie, int nbV, double tDebuff, double tempsRecup, double rangeDebuff) {
        super(name, coord, health, coin, vitesse, r, dmg, freqAtk, w, allie, nbV, tDebuff, tempsRecup, rangeDebuff);
    }

    public void debuff(double dt){
        List<Animal> animaux = getAnimauxCibles();
        if (!animaux.isEmpty()) {

            if (isActionSpecialePossible()) {
                if (!isChronoDefini()) {
                    setChrono(dt);
                    setChronoDefini(true);
                }

                if (getChrono() == dt) {
                    for (int i = 0; i < animaux.size() && i < getNbVictimes(); i++) {

                        actionDebuff(animaux.get(i), dt);
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

    private List<Animal> listesCiblesNonAffectees(){
        List<Animal> l= Utilitaire.entitesToAnimaux(getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getWorld().getEnnemis())));
        for (int i=l.size() -1; i>=0; i--){
            if(l.get(i).getSlowUntil()[0]!=0)
                l.remove(i);
        }
        return l;
    }

    public abstract void actionDebuff(Animal a, double dt);


}
