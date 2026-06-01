package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatMedecin extends Buffer {
    public ChatMedecin(double[] coord, GameWorld w) {
        super(coord, 7, 20, 10, 2, .5, 5, w, true, 3, 3, 5);
    }

    @Override
    public void actionBuff() {

    }

    @Override
    public List<Animal> getListeAnimaux() {
        return getWorld().getAllies();
    }
}
