package app.Modele.Projectile;

import app.Modele.Entites.Animaux.PouletProjectible;
import app.Modele.Entites.Entite;
import app.Modele.Utilitaires.Utilitaire;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ProjectileSimple {

    private static int nbId = 0;
    private int id;

    private final DoubleProperty x = new SimpleDoubleProperty();
    private final DoubleProperty y = new SimpleDoubleProperty();

    private Entite cible;
    private double dist;
    private double dx;
    private double dy;

    private final double speed;
    private double life;
    private final double damage;

    private double size;

    private double tempsMort;

    private boolean dead;

    public ProjectileSimple(PouletProjectible pouletProjectible, Entite cible) {

        this.id=nbId;
        nbId++;

        int dirX, dirY;
        if(pouletProjectible.getX() - cible.getX() >0)
            dirX = -1;
        else
            dirX = 1;

        if(pouletProjectible.getY() - cible.getY() >0)
            dirY = -1;
        else
            dirY = 1;

        this.x.set(pouletProjectible.getX()+ dirX*(pouletProjectible.getWorld().getTailleTile()* 0.1));
        this.y.set(pouletProjectible.getY() + dirY*(pouletProjectible.getWorld().getTailleTile() *0.1));

        this.cible=cible;
        this.dist = Utilitaire.distance(getX(), getY(), cible.getX(), cible.getY());
        this.dx = (cible.getX() - getX())/dist;
        this.dy = (cible.getY() - getY())/dist;

        speed = 3;
        life = 2;

        size = 10;

        tempsMort = 0;

        this.damage = pouletProjectible.getDamage();

        dead = false;
    }

    public void update(double dt) {

        if (tempsMort == 0)
            tempsMort = dt + life;

        x.set(x.get() + dx * speed);
        y.set(y.get() + dy * speed);

        life -= dt;

        if (dt >= tempsMort) {
            dead = true;
            tempsMort = 0;
        }
        if (Utilitaire.intersects(this, cible)) {
            cible.estAttaque(damage);
            dead = true;
            tempsMort = 0;
        }
    }

    public String getId() {
        return ""+id;
    }

    public DoubleProperty xProperty() {
        return x;
    }
    public DoubleProperty yProperty() {
        return y;
    }

    public double getX() {
        return x.get();
    }
    public double getY() {
        return y.get();
    }

    public double getDamage() {
        return damage;
    }

    public double getSize() {
        return size;
    }

    public boolean isDead() {
        return dead;
    }

    public Entite getCible() {
        return cible;
    }
}