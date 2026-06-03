package app.Modele.Utilitaires;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Utilitaire {

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1 -y2)*(y1-y2));
    }

    public static boolean intersects(Entite cible, Entite attaquant) {

        return distance(cible.getX(), cible.getY(), attaquant.getX(), attaquant.getY())<=attaquant.getRange();
    }

    public static boolean intersects(GameWorld w, Entite entite) {

        int[][] map = w.getMap();

        //Pixel en tile
        int tileX = (int) entite.getX()/w.getTailleTile();
        int tileY = (int) entite.getY()/w.getTailleTile();

        //Verif hors map (on peut delet ?)
        if (tileY < 0 || tileY >= map.length ||
                tileX < 0 || tileX >= map[0].length) {
            return false;
        }
        //Si coll avec batiment return true
        return map[tileY][tileX] == 2;
    }

    public static int divisionEuclidienne(double dividende, double diviseur){
        return (int) (dividende/diviseur);
    }


    public static double valAbs(double a){
        if(a<0)
            return -a;
        return a;
    }

    public static List<Entite> animauxToEntites(List<Animal> animaux){
        return new ArrayList<>(animaux);
    }

    public static List<Animal> entitesToAnimaux(List<Entite> entites){
        List<Animal> animaux = new ArrayList<>();
        for(Entite e: entites)
            animaux.add((Animal) e);
        return animaux;
    }
}
