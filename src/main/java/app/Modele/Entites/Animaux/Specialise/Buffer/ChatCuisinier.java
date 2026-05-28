package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatCuisinier extends Buffer {
    private double defense;

    public ChatCuisinier(double[] coord, GameWorld w) {

        super(coord, 8, 0, 5, .5, 3, w, true, 10, 3, 2);
        this.defense = 7;
    }


    @Override
    public void actionBuff() {

    }

    @Override
    public List<Animal> getListeAnimaux() {
        return getWorld().getAllies();
    }
}
