package app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Debuffer;
import app.Modele.GameWorld;

import java.util.List;

public class Stunner extends Debuffer {


    public Stunner(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {

        super(name, coord, w, statsLevels, allie);
    }


    public void debuff(double dt) {
        List<Animal> animaux = getListeCibles();
        if (!animaux.isEmpty()) {

            if (isActionSpecialePossible()) {
                if (!isChronoDefini()) {
                    setChrono(dt);
                    setChronoDefini(true);
                }

                if (getChrono() == dt) { //figer les bonhommes
                    double[] stunnedUntil= new double[2];
                    stunnedUntil[0]=getChrono()+getTempsAction();
                    this.setVitesse(0);
                    for (int i = 0; i < animaux.size() && i < getNbVictimes(); i++) {
                        stunnedUntil[1]=animaux.get(i).getVitesse();
                        animaux.get(i).setVitesse(0);
                        animaux.get(i).setCanAttack(false);
                        animaux.get(i).setStunnedUntil(stunnedUntil);
                    }

                } else if (getChrono() + getTempsAction() >= dt) { //defiger bonhommes + this ne peut pas stun
                    setVitesse(getvInitial());
                    setActionSpecialePossible(false);
                }
            }

            if (isChronoDefini() && getChrono() + getTempsAction() + getTempsRepo() <= dt) { //this peut stun à nouveau
                setActionSpecialePossible(true);
                setChronoDefini(false);
            }
        }
    }


}
