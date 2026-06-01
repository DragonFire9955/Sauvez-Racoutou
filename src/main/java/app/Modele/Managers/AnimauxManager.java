package app.Modele.Managers;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

public class AnimauxManager {



    public static Animal creerChatClassique(GameWorld w){
        return new Animal(EnnemisSpawn.randomCoord(w), 5, 0, 2, 5, 1, 1, w, true);
    }

    public static Animal creerPouletClassique(GameWorld w){
        return new Animal(EnnemisSpawn.randomCoord(w), 5, 1, 2, 5, 1, 1, w, false);
    }




}
