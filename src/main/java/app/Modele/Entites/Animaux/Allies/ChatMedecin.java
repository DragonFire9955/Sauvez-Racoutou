package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChatMedecin extends ChatClassique {
    public ChatMedecin(double[] coord, GameWorld w) {
        super(coord, 7, 10, 2, .5, 5, w);
    }
}
