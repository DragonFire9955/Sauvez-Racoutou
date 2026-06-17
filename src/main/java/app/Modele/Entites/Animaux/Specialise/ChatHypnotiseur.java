package app.Modele.Entites.Animaux.Specialise;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// pour l'instant extend animal comme bouclier (PAS DE CHRONO)
public class ChatHypnotiseur extends Animal{
    private double dmgSpecials;
    private double freqAtkSpeciale;
    private DoubleProperty rangeSpe;
    private Map<Animal, Double> hypnoEnCours;
    private double chronoSpe;

    public ChatHypnotiseur(double[] coord, GameWorld w, int actualLevel) {

        super("chatHypnotiseur", coord, w, StatsEntiteInitialiser.getStatsLevels("chatHypnotiseur"), actualLevel, true);

        List<Object[]> stats = StatsEntiteInitialiser.getStatsLevels("chatHypnotiseur");
        this.rangeSpe = new SimpleDoubleProperty((double) stats.get(actualLevel)[7]);
        this.freqAtkSpeciale = (double) stats.get(actualLevel)[8];
        this.dmgSpecials = (double) stats.get(actualLevel)[9];
        hypnoEnCours = new HashMap<>();
        chronoSpe=0;
    }

    @Override
    public void update(double dt) {
        super.update(dt);

        if(chronoSpe==0)
            chronoSpe=dt;

        if (((dt-chronoSpe)) >= freqAtkSpeciale) {
            attaqueSpeciale((Animal) getCibleSpeciale());
            chronoSpe = 0;
        }
    }

    @Override
    public void setStats(int actualLevel) {

        super.setStats(actualLevel);

        this.rangeSpe.setValue((double) getStatsLevels().get(actualLevel)[7]);
        this.dmgSpecials = ((double) getStatsLevels().get(actualLevel)[8]);
        this.freqAtkSpeciale = ((double) getStatsLevels().get(actualLevel)[9]);
    }

    public void attaqueSpeciale(Animal cible){

        if(cible!=null) {
            if (!hypnoEnCours.containsKey(cible)) {
                hypnoEnCours.put(cible, .0);
            }

            hypnoEnCours.put(cible, (hypnoEnCours.get(cible) + dmgSpecials));
            if (cible.getHealthProperty().get() <= hypnoEnCours.get(cible)) {
                cible.setAllie(true);
                cible.setHypno(true);
            }
        }
    }

    public Entite getCibleSpeciale(){

        if (getCiblesAccessibles(rangeSpe.getValue(), Utilitaire.animauxToEntites(getAnimauxCibles())).isEmpty()) return null ;
        return getCiblesAccessibles(rangeSpe.getValue(), Utilitaire.animauxToEntites(getAnimauxCibles())).getFirst();
    }

    public DoubleProperty getRangeSpeProperty() {
        return rangeSpe;
    }
}
