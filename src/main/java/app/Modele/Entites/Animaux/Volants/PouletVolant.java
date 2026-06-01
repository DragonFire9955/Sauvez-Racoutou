package app.Modele.Entites.Animaux.Volants;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Utilitaire;

public class PouletVolant extends Volant {

    //On récup le 1er Racoutou trouvé
    private Animal cible;

    //Distances entre les coordonnées des 2 cibles
    private double dx;
    private double dy;

    //Distance réelle (en gros la diago de pythagore
    private double norme;

    //Directions
    private double dirX;
    private double dirY;

    public PouletVolant(double[] coord, GameWorld w) {

        super(coord, 10, 2, 5, 2, 10, 1, w, false);

        cible = this.getAnimauxCibles().stream().filter(animal -> animal instanceof Racoutou).findFirst().orElse(null);

        norme = Math.sqrt(Math.pow(cible.getX()-this.getX(), 2) + Math.pow(cible.getY()-this.getY(), 2));

        dx = cible.getX()-this.getX();
        dy = cible.getY()-this.getY();

        dirX = dx / norme;
        dirY = dy / norme;
    }

    @Override
    public void update(double dt) {

        super.update(dt);

        this.deplacement();
    }

    @Override
    public void deplacement() {

        this.setX(this.getX() + dirX*getVitesse());
        this.setY(this.getY() + dirY*getVitesse());
    }
}
