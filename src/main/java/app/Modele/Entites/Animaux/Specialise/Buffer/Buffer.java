package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.GameWorld;

import java.util.List;

public abstract class Buffer extends Specialise {


    public Buffer(double[] coord, double health, double vitesse, double r, double dmg, double freqAtk, GameWorld w, boolean allie, double buffRange, double tempsBuff, double tempsRepo) {
        super(coord, health, vitesse, r, dmg, freqAtk, w, allie, buffRange, tempsBuff, tempsRepo);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        buff();
    }

    public void buff(){
        //List<Animal> cibles =
    }

    public abstract void actionBuff();





}
