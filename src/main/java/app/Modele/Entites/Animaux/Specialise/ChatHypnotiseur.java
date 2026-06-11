package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// pour l'instant extend animal comme bouclier (PAS DE CHRONO)
public class ChatHypnotiseur extends Animal{
    private double dmgSpecials;
    private double freqAtkSpeciale;
    private double rangeSpe;
    private Map<Animal, Double> hypnoEnCours;
    private double chronoSpe;

    public ChatHypnotiseur(double[] coord, GameWorld w) {

        super("chatHypnotiseur", coord, w, StatsEntiteInitialiser.getStatsLevels("chatHypnotiseur"), true);

        List<Object[]> statsJSPQUOIFAIRE = StatsEntiteInitialiser.getStatsLevels("chatHypnotiseur");
        this.dmgSpecials = (double) statsJSPQUOIFAIRE.get(0)[7];
        this.freqAtkSpeciale = (double) statsJSPQUOIFAIRE.get(0)[8];
        this.rangeSpe = (double) statsJSPQUOIFAIRE.get(0)[9];
        hypnoEnCours = new HashMap<>();
        chronoSpe=0;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        if(chronoSpe==0)
            chronoSpe=dt;

        if (((dt-chronoSpe)) >= freqAtkSpeciale) {
            System.out.println("CIBLE: "+getCibleSpeciale());
            attaqueSpeciale((Animal) getCibleSpeciale());
            chronoSpe = 0;
        }
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.dmgSpecials = ((double) getStatsLevels().get(actualLevel)[7]);
        this.freqAtkSpeciale = ((double) getStatsLevels().get(actualLevel)[8]);
        this.rangeSpe = ((double) getStatsLevels().get(actualLevel)[9]);
    }

    public void attaqueSpeciale(Animal cible){

        if(cible!=null) {
            System.out.println(!hypnoEnCours.containsKey(cible));
            if (!hypnoEnCours.containsKey(cible)) {
                hypnoEnCours.put(cible, .0);
            }

            hypnoEnCours.put(cible, (hypnoEnCours.get(cible) + dmgSpecials));
            if (cible.getHealthProperty().get() <= hypnoEnCours.get(cible)) {
                cible.setAllie(true);
                System.out.println("new allié bidiboup");
            }
        }
    }

    public Entite getCibleSpeciale(){

        if (getCiblesAccessibles(rangeSpe, Utilitaire.animauxToEntites(getAnimauxCibles())).isEmpty()) return null ;
        return getCiblesAccessibles(rangeSpe, Utilitaire.animauxToEntites(getAnimauxCibles())).getFirst();
    }
/*
    @Override
    public void actionDebuff(Animal a, double dt) {

        //Si a pas encore hypnoEnCours et pas déjà hypnotisé
        if(!hypnoEncours.containsKey(a) && a.getInverseUntil()!=0){
            hypnoEncours.put(a, dt);
        }

        for (Map.Entry<Animal, Double> entry: hypnoEncours.entrySet()){
            if(!getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCibles())).contains(entry.getKey()))
                hypnoEncours.remove(entry.getKey());
            else if(dt >= entry.getValue()+ tempsDirscours) {
                System.out.print(a.isAllie());
                a.setAllie(true);
                System.out.println("  bidiboup abracadabra INVERSEEEEE  " + a.isAllie());
                a.setInverseUntil(getTempsAction()+ dt );

            }

        }

    }

 */

}
