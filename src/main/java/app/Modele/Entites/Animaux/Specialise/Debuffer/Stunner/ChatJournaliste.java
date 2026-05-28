package app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatJournaliste extends Stunner {


    public ChatJournaliste(double[] coord,  GameWorld w) {
        super(coord, 8, 3, .5, .5, 1, w, true, 1, 3, 5, 10);
    }



    @Override
    public List<Animal> getListeAnimaux() {
        return getAnimauxCiblesAccessibles(getRangeEffect(), getWorld().getEnnemis());
    }
}
