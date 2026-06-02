package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class ChatCuisinier extends Buffer {


    public ChatCuisinier(double[] coord, GameWorld w) {

        super(coord, 8, 50, 0, 5, .5, 3, w, true, 15, 3, .5, new ArrayList<>());
        getListeBuff().add(0.25);
    }




    @Override
    public void actionBuff(Animal cible) {

    }
}
