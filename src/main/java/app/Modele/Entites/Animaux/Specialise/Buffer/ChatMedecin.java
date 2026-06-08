package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class ChatMedecin extends Buffer {
    public ChatMedecin(double[] coord, GameWorld w) {
        super("Chat médecin", coord, 7, 20, 10, 2, .5, 5, w, true, 3, 3, 5, new ArrayList<>());
        getListeBuff().add(1.);
    }
}
