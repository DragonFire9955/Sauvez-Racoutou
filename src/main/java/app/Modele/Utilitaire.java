package app.Modele;

import app.Modele.Entites.Animaux.Animaux;
import app.Modele.Entites.Entite;

public class Utilitaire {

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1 -y2)*(y1-y2));
    }

    public static boolean intersects(Entite cible, Entite attaquant) {
        double dx = (cible.getX()+cible.getRange()/2) - (attaquant.getX()+cible.getRange()/2);
        double dy = (cible.getY()+cible.getRange()/2) - (attaquant.getY()+cible.getRange()/2);
        return dx * dx + dy * dy < Math.pow(cible.getRange(), 2);
    }
}
