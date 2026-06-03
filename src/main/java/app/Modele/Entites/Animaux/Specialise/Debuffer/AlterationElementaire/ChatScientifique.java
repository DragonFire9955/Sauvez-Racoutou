package app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.List;

public class ChatScientifique extends AlterationElementaire {

    private double facteurDivVitesse;
    private double facteurDivForce;


    public ChatScientifique(double[] coord, GameWorld w) {
        super(coord, 10, 20, 3, 5, 1, 1, w, true, 1, 3, 5, 1000);
        facteurDivForce=100;
        facteurDivVitesse=10;
    }


    public void actionDebuff(Animal a) {
        a.setSlowUntil((getChrono() + getTempsAction()), a.getVitesse(), a.getDamage() );
        System.out.println(a.getClass().getName()+" dmg:" + a.getDamage() +" v: "+a.getVitesse());
        a.setDamage(a.getDamage()/facteurDivForce);
        a.setVitesse(a.getVitesse()/facteurDivVitesse);
        System.out.println(a.getClass().getName()+" dmg:" + a.getDamage() +" v: "+a.getVitesse());
        System.out.println();
        System.out.println("slow");
    }


    public List<Animal> getListeCibles() {
        return getWorld().getEnnemis();
    }



}
