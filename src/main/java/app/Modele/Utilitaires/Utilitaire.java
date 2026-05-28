package app.Modele.Utilitaires;

import app.Modele.Entites.Entite;

public class Utilitaire {

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1 -y2)*(y1-y2));
    }

    public static boolean intersects(Entite cible, Entite attaquant) {

        double maxRange = Math.max(cible.getRange(), attaquant.getRange());

        double dx = (cible.getX()+cible.getRange()/2) - (attaquant.getX()+cible.getRange()/2);
        double dy = (cible.getY()+cible.getRange()/2) - (attaquant.getY()+cible.getRange()/2);

        return Math.abs(dx) + Math.abs(dy) < Math.pow(maxRange, 2);
    }

    public static int divisionEuclidienne(double dividende, double diviseur){
        return (int) (dividende/diviseur);
    }



}
