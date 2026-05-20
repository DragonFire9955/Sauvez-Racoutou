package app.Modele.Managers;

import app.Modele.CollisionUtil;
import app.Modele.Entites.Animaux;
import app.Modele.Entites.AnimauxFolder.Ennemis.PouletClassique;

public class EnnemyManager extends AnimauxManager {

    public EnnemyManager(){
        super();
        //addAnimal(new Racoutou();
    }

    public void handleCollisions() {

/*        for (Animaux e : getAnimaux()) {
            if (e instanceof PouletClassique) {
                if (CollisionUtil.intersects(e, e.getWorld().getAllies().getFirst())) {
                    System.out.println("touche !");
                    e.destroy();
                }
            }
        }*/
    }

}
