package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;


public class PouletMenotte extends PouletClassique {

    private static int nbVictimes = 3;
    private static double stun = 2; //en secondes
    private static double tempsRecup = 5;
    private static int rangeStun = 100;
    private boolean chronoDefini;
    private boolean peutStun;

    private static double chrono;

    public PouletMenotte(GameWorld w) {

        super(EnnemisSpawn.randomCoord(w), w);
        chronoDefini = false;
        peutStun=true;
    }

    @Override
    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        List<Animaux> cibles = getCiblesAccessibles(rangeStun);

        if (!cibles.isEmpty()) {

            if (peutStun) {
                if (!chronoDefini) {
                    chrono = dt;
                    chronoDefini = true;
                }


                if (chrono == dt) { //figer les bonhommes
                    this.setCanMove(false);
                    for (int i = 0; i < cibles.size() && i < nbVictimes; i++) {
                        cibles.get(i).setCanMove(false);
                        cibles.get(i).setCanAttack(false);
                        System.out.println("stun");
                    }

                } else if (chrono + stun == dt) { //defiger bonhommes + this ne peut pas stun
                    for (int i = 0; i < cibles.size() && i < nbVictimes; i++) {
                        cibles.get(i).setCanMove(true);
                        cibles.get(i).setCanAttack(true);
                        System.out.println("destun= true");
                    }
                    this.setCanMove(true);
                    this.peutStun = false;
                    System.out.println("debut coll down");
                }

            }

            if (chronoDefini && chrono + stun + tempsRecup == dt) { //this peut stun à nouveau
                this.peutStun = true;
                chronoDefini = false;
                System.out.println("fin cool down");
            }
        }
    }


    public void stun(double dt) {
        List<Animaux> cibles = getCiblesAccessibles(rangeStun);
        this.setCanMove(false);
        for(int i=0; i<cibles.size() && i<nbVictimes; i++){
            cibles.get(i).setCanAttack(false);
            cibles.get(i).setCanMove(false);
        }

        //PAUSE DE X SEC
        for(int i=0; i<cibles.size() && i<nbVictimes; i++){
            cibles.get(i).setCanAttack(true);
            cibles.get(i).setCanMove(true);
        }
        this.setCanMove(true);

    }

}
