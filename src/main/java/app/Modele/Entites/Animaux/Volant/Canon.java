package app.Modele.Entites.Animaux.Volant;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public class Canon extends Animal {
    private double scaleMax = 2.0;

    //freqAtk = freq tirage max
    protected Canon(double[] coord, double health, double range, double dmg, double freqAtk, GameWorld w) {
        super(coord, 20, 30, 0, 10, w, true);
        scaleMax =2;
    }

    @Override
    public void attaquer() {
    }

    @Override
    public Entite getCible() {
        List<Animal> cibles= getAnimauxCiblesAccessibles(getRange(), getWorld().getEnnemis());
        if(cibles.isEmpty()) return null;
        int i=0;
        while(i<cibles.size()
              && !(cibles.get(i) instanceof Volant)
              && (int) (Utilitaire.distance(getX(), getY(), cibles.get(i).getX(), cibles.get(i).getY())) != scaleMax){
            i++;
        }
        if (i!=cibles.size()) return cibles.get(i);
        return cibles.getFirst();
    }

    /*
    Ennemis:
        - volants:
            ceux qui volent et largue des bombes: tjrs volant
            ceux qui volent jusqu'à ce poser juste à coté de racoutou (force+++, pv--): volant puis mobile
     */
}
