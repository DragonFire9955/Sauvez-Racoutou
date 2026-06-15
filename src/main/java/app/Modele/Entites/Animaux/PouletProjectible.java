package app.Modele.Entites.Animaux;

import app.Modele.GameWorld;
import app.Modele.Entites.Entite;
import app.Modele.Projectile.ProjectileSimple;
import app.Modele.Utilitaires.Utilitaire;

import java.util.List;

public class PouletProjectible extends Animal {

    private double cooldown;
    private final double initVit;

    private double projectileDmg;

    public PouletProjectible(String name, double[] coord, GameWorld w, List<Object[]> statsLevels, boolean allie) {
        super(name, coord, w, statsLevels, 0, allie);

        cooldown = 0;
        initVit = getVitesse();
    }

    @Override
    public void update(double dt) {

        super.update(dt);
        ;
        if (cooldown == 0)
            cooldown = dt + getFreqAtk();

        List<Entite> cibles = getCiblesAccessibles(getRange(), Utilitaire.animauxToEntites(getWorld().getAlliesAnimaux()));
        if (cibles.isEmpty()) return;
        Entite cible = cibles.getFirst();

        double dist = Utilitaire.distance(getX(), getY(), cible.getX(), cible.getY());
        if (dist < getRange()) {
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
}