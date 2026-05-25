package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChatJournaliste extends ChatClassique {
    public ChatJournaliste(double[] coord,  GameWorld w) {
        super(coord, 8, 10, .5, .5, 1, w);
    }
}
