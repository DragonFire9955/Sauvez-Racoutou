package app.Modele;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Animaux.Ennemis.PouletClassique;
import app.Modele.Entites.Animaux.Ennemis.PouletMenotte;

import java.util.ArrayList;
import java.util.HashMap;

public class Vague {

    public static HashMap<Integer, ArrayList<Animaux>> creerVague1(GameWorld w){

        HashMap<Integer, ArrayList<Animaux>> v= new HashMap<>();

        v.put(0, lAnimaux(3, 3, w));
        v.put(5, lAnimaux(1, 5, w));
        v.put(10, lAnimaux(7, 2, w));

        return v;
    }

    public static ArrayList<Animaux> lAnimaux(int nbClassique, int nbMenotte, GameWorld w){

        ArrayList<Animaux> l = new ArrayList<>();
        for(int i=0; i<nbClassique; i++)
            l.add(new PouletClassique(w));

        for(int i=0; i<nbMenotte; i++)
            l.add(new PouletMenotte(w));

        return l;
    }
}
