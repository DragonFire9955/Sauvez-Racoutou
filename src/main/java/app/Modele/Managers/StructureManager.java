package app.Modele.Managers;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Entites.Barrage.Poubelle;

public class StructureManager {

    public static Entite creerStructure(int id, double[] coords, GameWorld gameWorld) {
        switch (id) {
            case 100:
                return new Poubelle(coords, 100.0, 5, 16.0, gameWorld);
            case 101:
                return AnimauxManager.creerChatClassique(coords, gameWorld);
            default:
                return null;
        }
    }


    public static int getPrix(int id) {
        switch (id) {
            case 100:
                return 5;
            case 101:
                return 10;
            case 102:
                return 30;
            case 103:
                return 50;
            default:
                return 0;
        }
    }
}