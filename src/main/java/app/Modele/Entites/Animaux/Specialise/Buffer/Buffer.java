package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class Buffer extends Specialise {

    private double buffRange;
    private double tempsBuff;
    private double tempsRepo;

    private ArrayList<Double> listeBuff;

    public Buffer(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie, ArrayList<Double> listeBuff) {

        super(name, coord, w, statsLevels, allie);

        this.buffRange = (double) statsLevels.get(0)[7];
        this.tempsBuff = (double) statsLevels.get(0)[8];
        this.tempsRepo = (double) statsLevels.get(0)[9];

        this.listeBuff=listeBuff;
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if(!getAnimauxCiblesAccessibles().isEmpty() && getChrono()==0){
            setChrono(dt);
            buff();
        } else if (getChrono()>=getTempsRepo()) {
            setChrono(0);
        }
    }

    public void buff(){
        Animal cible = getAnimauxCiblesAccessibles().getFirst();
        cible.setHealth(cible.getHealthProperty().getValue() + getListeBuff().getFirst());

    }



    public List<Animal> getAnimauxCiblesAccessibles(){

        return Utilitaire.entitesToAnimaux(getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getAnimauxCopains())));
    }

    public ArrayList<Double> getListeBuff() {
        return listeBuff;
    }


    // Retourne la liste d'animaux du même camp de classe différente classés par distance croissante et pv croissant
    public List<Animal> getAnimauxCopainsClasses(){

        List<Animal> entitesTriees = new ArrayList<>();
        List<Animal> copains = getAnimauxCopains();
        int i;

        for(Animal a: copains) {
            System.out.println(this.getClass());
            if(a.getClass() == this.getClass()) continue;
            i= 0;
            //Tant que distance supérieur ET pv supérieur
            while (i < entitesTriees.size()
                    && Utilitaire.distance(this.getX(), this.getY(), a.getX(), a.getY())
                    > Utilitaire.distance(this.getX(), this.getY(), entitesTriees.get(i).getX(), entitesTriees.get(i).getY())) {
                i++;
            }
            entitesTriees.add(i, a);
        }
        return entitesTriees;
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
