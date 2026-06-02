package app.Modele.Entites.Animaux.Volants.ChienIntermittent;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Volants.Volant;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public class Canon extends Animal {
    private ChienIntermittent chien;
    private double scaleMax = 10;
    private double vitesseVol;

    protected Canon(double[] coord, double health, double range, double dmg, double freqAtk, GameWorld w) {
        super(coord, health, range, dmg, freqAtk, w, true);
        chien= new ChienIntermittent(coord, w, this);
        vitesseVol=20;
    }


    /*
    @Override
    public Entite getCible() {
        return null;
    }


     */
    public Entite getCible() {
        List<Animal> cibles= getAnimauxCiblesAccessibles(getRange(), getWorld().getEnnemis());
        if(cibles.isEmpty()) return null;
        int i=0;
        while(i<cibles.size()
                && !(cibles.get(i) instanceof Volant)
                && (Utilitaire.distance(getX(), getY(), cibles.get(i).getX(), cibles.get(i).getY()))<scaleMax-2
                && (Utilitaire.distance(getX(), getY(), cibles.get(i).getX(), cibles.get(i).getY()))>scaleMax+2){
            i++;
        }
        if (i!=cibles.size()) return cibles.get(i);
        return cibles.getFirst();
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if(getCible()!=null)
            tirer();
    }

    public void tirer(){
        chien.fly(getCible());
    }

    /*
    Ennemis:
        - volants:
            ceux qui volent et largue des bombes: tjrs volant
            ceux qui volent jusqu'à ce poser juste à coté de racoutou (force+++, pv--): volant puis mobile
     */
}
