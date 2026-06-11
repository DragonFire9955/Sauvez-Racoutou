package app.Modele.Entites.Animaux.Specialise;

import app.Modele.GameWorld;
import app.Modele.Entites.Entite;
import app.Modele.ProjectileSimple;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public class PouletProjectible extends Specialise {

    private double cooldown;
    private final double initVit;

    private double projectileDmg;

    public PouletProjectible(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {
        super(name, coord, w, statsLevels, allie);

        cooldown = 0;
        initVit = getVitesse();
    }

    @Override
    public void update(double dt) {

        super.update(dt);
        ;
        if (cooldown == 0)
            cooldown = dt + getTempsRepo();

        List<Entite> cibles = getCiblesAccessibles(getRangeEffect(), Utilitaire.animauxToEntites(getWorld().getAlliesAnimaux()));
        if (cibles.isEmpty()) return;
        Entite cible = cibles.getFirst();

        double dist = Utilitaire.distance(getX(), getY(), cible.getX(), cible.getY());
        if (dist < getRangeEffect()) {
            setVitesse(0);

            if (dt >= cooldown) {

                getWorld().addProjectile(new ProjectileSimple(this, cible));

                cooldown = 0;
            }
        }
        else {
            setVitesse(initVit);
        }
    }

    public double getProjectileDmg() {
        return projectileDmg;
    }
}