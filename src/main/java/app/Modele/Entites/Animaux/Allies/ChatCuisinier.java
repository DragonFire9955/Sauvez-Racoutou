package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChatCuisinier extends ChatClassique {
    private double defense;

    public ChatCuisinier(double[] coord, GameWorld w) {

        super(coord, 8, 0, 5, .5, 3, w);
        this.defense = 7;
    }




}
