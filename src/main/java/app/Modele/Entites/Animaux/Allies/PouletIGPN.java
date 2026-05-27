package app.Modele.Entites.Animaux.Allies;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.GameWorld;

import java.util.List;

public class PouletIGPN extends ChatClassique{

    private double rangeSlow=1000;
    private double facteurDivVitesse=100;
    private double facteurDivForce=10;
    private int nbVictimes=2;
    private boolean peutSlow;
    private double tempsSlow= 3;
    private double coolDown=5;

    private boolean chronoDefini;
    private static double chrono;

    public PouletIGPN(double[] coord, GameWorld w){
        super(coord, w);
            chronoDefini=false;
            peutSlow=true;
            setDamage(0);
    }

    @Override
    public void update(double dt) { //dt = dt_controleur * 0.017
        super.update(dt);
        slow(dt);
    }

    public void slow(double dt){
        List<Animaux> cibles = listesCiblesPasSlow();
        System.out.println(cibles.size()+" taille cibles");
        if (!cibles.isEmpty()) {

            if (peutSlow) {
                if (!chronoDefini) {
                    chrono = dt;
                    chronoDefini = true;
                }

                if (chrono == dt) {
                    for (int i = 0; i < cibles.size() && i < nbVictimes; i++) {
                        cibles.get(i).setDamage(cibles.get(i).getDamage()/facteurDivForce);
                        cibles.get(i).setVitesse(cibles.get(i).getVitesse()/facteurDivVitesse);
                        cibles.get(i).setStunnedUntil(chrono + tempsSlow);
                        System.out.println(dt);
                        System.out.println("slow");
                    }

                } else if (chrono + tempsSlow>= dt) { //defiger bonhommes + this ne peut pas stun
                    /*for (int i = 0; i < cibles.size() && i < nbVictimes; i++) {
                        cibles.get(i).setCanMove(true);
                        cibles.get(i).setCanAttack(true);

                        System.out.println("destun= true");
                    }
                     */
                    this.peutSlow = false;
                    System.out.println("debut cooldown");
                }
                System.out.println(chrono + "chrono");
                System.out.println("dt " + dt);
            }

            if (chronoDefini && chrono + tempsSlow + coolDown == dt) { //this peut stun à nouveau
                this.peutSlow = true;
                chronoDefini = false;
                System.out.println("fin cool down");
            }
        }
    }

    private List<Animaux> listesCiblesPasSlow(){
        List<Animaux> l= getCiblesAccessibles(rangeSlow);
        for (int i=l.size() -1; i>=0; i--){
            if(l.get(i).getSlowUntil()[0]!=0)
                l.remove(i);
        }
        return l;
    }

}
