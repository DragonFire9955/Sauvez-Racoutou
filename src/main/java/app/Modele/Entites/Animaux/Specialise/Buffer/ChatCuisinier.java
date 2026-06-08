package app.Modele.Entites.Animaux.Specialise.Buffer;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class ChatCuisinier extends Buffer {

    public ChatCuisinier(double[] coord, GameWorld w) {

        super("Chat Cuisinier", coord, 8, 50, 0, 5, .5, 3, w, true, 15, 3, .5, new ArrayList<>());
        getListeBuff().add(0.25);
        setStatsLevels(getStatsLevels());
    }

    @Override
    public void actionBuff(Animal cible) {

    }

    /// TODO : une classe pour les stats ?
    @Override
    public void setStatsLevels(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0,     //Speed
                5,     //Range
                5,     //Damage
                1,     //FreqAtk
                15,    //BuffRange
                3,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12,     //Health   (+4)
                0,      //Speed
                5,      //Range
                7,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20,     //Health   (+8)
                0,      //Speed
                8,      //Range     (+3)
                10,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30,     //Health   (+10)
                0,      //Speed
                10,     //Range     (+2)
                15,     //Damage   (+5)
                2,      //FreqAtk (+.5)
                25,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }
}
