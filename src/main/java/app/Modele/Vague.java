package app.Modele;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.PouletMenotte;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Managers.EnnemisSpawn;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.ArrayList;
import java.util.HashMap;

public class Vague {

    public static IntegerProperty currentWave = new SimpleIntegerProperty();
    public static int waveDuration;

    public static HashMap<Integer, ArrayList<Animal>> creerVague1(GameWorld w){

        currentWave.setValue(1);
        waveDuration = 20;

        HashMap<Integer, ArrayList<Animal>> v= new HashMap<>();

        v.put(0, lAnimaux(3, 3, w));
        v.put(5, lAnimaux(1, 5, w));
        v.put(10, lAnimaux(7, 2, w));

        return v;
    }

    public static ArrayList<Animal> lAnimaux(int nbClassique, int nbMenotte, GameWorld w){

        ArrayList<Animal> l = new ArrayList<>();
        for(int i=0; i<nbClassique; i++)
            l.add(AnimauxManager.creerPouletClassique(w));

        for(int i=0; i<nbMenotte; i++)
            l.add(new PouletMenotte(EnnemisSpawn.randomCoord(w), w));

        return l;
    }
}
