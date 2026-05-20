package app.Modele;

import app.Modele.Entites.Animaux;

public class Utilitaire {

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1 -y2)*(y1-y2));
    }

    public static boolean intersects(Animaux a, Animaux b) {
        double dx = (a.getX()+a.getRange()/2) - (b.getX()+a.getRange()/2);
        double dy = (a.getY()+a.getRange()/2) - (b.getY()+a.getRange()/2);
        return dx * dx + dy * dy < Math.pow(a.getRange(), 2);
    }
}
