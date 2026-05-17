package app.Managers;

import app.Entites.Animaux;
import app.Entites.AnimauxFolder.Allies.Racoutou;
import app.Entites.AnimauxFolder.Ennemis.PouletClassique;

import java.util.ArrayList;
import java.util.List;

public class AnimauxManager {

    private List<Animaux> animaux = new ArrayList<>();

    private Animaux racoutou = new Racoutou();

    public List<Animaux> getAnimaux() {
        return animaux;
    }
    public void addAnimal(Animaux e) {
        animaux.add(e);
    }

    public void update(double dt) {

        for (Animaux a : animaux) {
            a.update(dt);
        }

        handleCollisions();
    }


    private void handleCollisions() {

        for (Animaux e : animaux) {
            if (e instanceof PouletClassique) {
                if (CollisionUtil.intersects(e, racoutou)) {
                    System.out.println("touche !");
                    racoutou.takeDamage(e.getHealthProperty().getValue());  //Là j'enlève à Racoutou la vie restant de l'ennemi.
                    e.destroy();
                }
            }
        }
    }

    public Animaux getRacoutou() {
        return racoutou;
    }

    public Animaux getLast(){
        if(animaux.size()==0)
            return null;
        else
            return animaux.get(animaux.size()-1);
    }

}
