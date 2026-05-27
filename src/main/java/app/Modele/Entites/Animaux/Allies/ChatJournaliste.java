package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.GameWorld;

import java.util.List;

public class ChatJournaliste extends ChatClassique {

    private static int nbVictimes = 3;
    private static double stun = 2; //en secondes
    private static double tempsRecup = 5;
    private static int rangeStun = 100;
    private boolean chronoDefini;
    private boolean peutStun;

    private static double chrono;

    public ChatJournaliste(double[] coord,  GameWorld w) {
        super(coord, 8, 3, .5, .5, 1, w);
        peutStun=true;
        chronoDefini=false;
    }

    @Override
    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        stun(dt);
    }

    private void stun(double dt) {
        List<Animaux> cibles = getCiblesAccessibles(rangeStun);
        System.out.println(cibles.size()+" taille cibles");
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
                        cibles.get(i).setStunnedUntil(chrono + stun);
                        System.out.println("stun");
                    }

                } else if (chrono + stun >= dt) { //defiger bonhommes + this ne peut pas stun
                    /*for (int i = 0; i < cibles.size() && i < nbVictimes; i++) {
                        cibles.get(i).setCanMove(true);
                        cibles.get(i).setCanAttack(true);

                        System.out.println("destun= true");
                    }
                     */
                    this.setCanMove(true);
                    this.peutStun = false;
                    System.out.println("debut cooldown");
                }
                System.out.println(chrono + "chrono");
                System.out.println("dt " + dt);
            }

            if (chronoDefini && chrono + stun + tempsRecup == dt) { //this peut stun à nouveau
                this.peutStun = true;
                chronoDefini = false;
                System.out.println("fin cool down");
            }
        }
    }
}
