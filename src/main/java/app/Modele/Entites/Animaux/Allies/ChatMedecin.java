package app.Modele.Entites.Animaux.Allies;

import app.Modele.GameWorld;

public class ChatMedecin extends ChatClassique {
    public ChatMedecin(double[] coord, double health, double vitesse, double dmg, double range, GameWorld w) {
        super(coord, health, vitesse, dmg, range, w);
    }
}
