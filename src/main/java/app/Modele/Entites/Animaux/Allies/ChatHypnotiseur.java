package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChatHypnotiseur extends ChatClassique {
    private double tempsDirscours;

    public ChatHypnotiseur(double[] coord, GameWorld w) {

        super(coord, 6, 0, 10, 1, 1, w);
        this.tempsDirscours = 50;
    }
}
