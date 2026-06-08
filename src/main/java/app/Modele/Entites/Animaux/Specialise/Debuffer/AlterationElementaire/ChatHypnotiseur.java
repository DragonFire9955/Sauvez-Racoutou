package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatHypnotiseur extends AlterationElementaire {
    private double tempsDirscours;
    //private Map <Animal, double> hypno;

    public ChatHypnotiseur(double[] coord, GameWorld w) {

        super("Chat hypnotiseur", coord, 6, 40, 0, 10, 1, 1, w, true, 1, 5, 10, 5);
        this.tempsDirscours = 5;
    }

    @Override
    public void actionDebuff(Animal a, double dt) {
        System.out.print(a.isAllie());
        a.setAllie(true);
        System.out.println("  bidiboup abracadabra INVERSEEEEE  " + a.isAllie());
    }

}
