package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class Buffer extends Specialise {

    private ArrayList<Double> listeBuff;
    public Buffer(double[] coord, double health, int coin, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie, double buffRange, double tempsBuff, double tempsRepo, ArrayList<Double> listeBuff) {
        super(coord, health, coin, vitesse, r, dmg, freqAtk, w, allie, tempsBuff, tempsRepo, buffRange);
        this.listeBuff=listeBuff;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        System.out.println("super update");
        if(!getAnimauxCiblesAccessibles().isEmpty() && getChrono()==0){
            setChrono(dt);
            buff();
        } else if (getChrono()>=getTempsRepo()) {
            setChrono(0);
        }
    }

    public void buff(){
        Animal cible = getAnimauxCiblesAccessibles().getFirst();
        System.out.println(cible.getClass().getName() +"  " + cible.getHealthProperty().getValue());
        cible.setHealth(cible.getHealthProperty().getValue() + getListeBuff().getFirst());

    }



    public List<Animal> getAnimauxCiblesAccessibles(){

        return Utilitaire.entitesToAnimaux(getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCopains())));
    }

    public ArrayList<Double> getListeBuff() {
        return listeBuff;
    }

    @Override
    public Entite getDirection() {
        if(getAnimauxCopains().isEmpty()) {
            System.out.println("pas de cops");
            return null;
        }
        return getAnimauxCopainsClasses().getFirst();
    }
}
