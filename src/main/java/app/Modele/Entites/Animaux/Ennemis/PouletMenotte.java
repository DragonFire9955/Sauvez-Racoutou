package app.Modele.Entites.Animaux.Ennemis;

import app.Modele.GameWorld;

public class PouletMenotte extends PouletClassique{

    private static int nbVictimes=1;
    private static int stun=2;

    public PouletMenotte(GameWorld w){
        super(w);
    }

    @Override
    public void attaquer(){

        //getCiblesAccessibles().getFirst().stun
    }
}
