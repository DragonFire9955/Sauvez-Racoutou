package app.Modele.Managers;

import app.Modele.Entites.Animaux.Animaux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public abstract class AnimauxManager {

    private ObservableList<Animaux> animaux;
    //private List<Animaux> animaux;

    public AnimauxManager(){
        animaux = FXCollections.observableArrayList();
    }
    public ObservableList<Animaux> getAnimaux() {
        return animaux;
    }
    public void addAnimal(Animaux e) {
        animaux.add(e);
    }

    public abstract void handleCollisions();

    public void update(double dt) {

        for (Animaux a : animaux) {
            a.update(dt);
        }
    }




    public Animaux getLast(){
        if(animaux.isEmpty())
            return null;
        else
            return animaux.get(animaux.size()-1);
    }

    private void gestionMorts(){
        Iterator<Animaux> iterator = animaux.iterator();
        while (iterator.hasNext()) {
            Animaux a = iterator.next();    //Je le stocke car si on le save pas on peux pas l'use, car on passera au suivant si on remet iterator.next

            if (!a.isAlive()) {
                iterator.remove();

            }
        }

    }

}
