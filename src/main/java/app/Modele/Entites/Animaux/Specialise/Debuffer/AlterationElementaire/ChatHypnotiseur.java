package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatHypnotiseur extends AlterationElementaire {
    private double tempsDirscours;

    public ChatHypnotiseur(double[] coord, GameWorld w) {

        super(coord, 6, 0, 10, 1, 1, w, true, 1, 5, 10, 5);
        this.tempsDirscours = 5;
    }

    @Override
    public void actionDebuff(Animal a) {

    }

    @Override
    public List<Animal> getListeAnimaux() {
        return List.of();
    }
}
