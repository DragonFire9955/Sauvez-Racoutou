package app.Modele.Utilitaires;

import app.Modele.Entites.Entite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class StatsEntiteInitialiser {

    public static List<Object[]> getStatsLevels(String name) {

        List<Object[]> statsLevels = new ArrayList<>();


        switch (name) {
            case "Racoutou":
                initStatsRacoutou(statsLevels);
                break;
// CLASSIQUES
            case "Chat classique":
                initStatsChatClassique(statsLevels);
                break;
            case "Poulet classique":
                initStatsPouletClassique(statsLevels);
                break;
// SPECIALISES
            case "Poulet bouclier":
                initStatsPouletBouclier(statsLevels);
                break;
    // BUFFER
            case "Chat cuisinier":
                initStatsChatCuisinier(statsLevels);
                break;
            case "Chat médecin":
                initStatsChatMedecin(statsLevels);
                break;
            case "Poulet conservateur":
                initStatsPouletConservateur(statsLevels);
                break;
    // DEBUFFER
        // ALTERATIONS ELEMENTAIRES
            case "Chat scientifique":
                initStatsChatScientifique(statsLevels);
                break;
            case "Chat hypnotiseur":
                initStatsChatHypnotiseur(statsLevels);
                break;
        // STUNNER
            case "Chat journaliste":
                initStatsChatJournaliste(statsLevels);
                break;
            case "Poulet menottes":
                initStatsPouletMenottes(statsLevels);
                break;
// VOLANTS
            case "Poulet volant":
                initStatsPouletVolant(statsLevels);
                break;
            case "Chien intermittent":
                initStatsChienInterim(statsLevels);
                break;
// CREUSANT
            case "Poulet mineur":
                initStatsPouletMineur(statsLevels);
                break;
            case "Taupe":
                initStatsTaupe(statsLevels);
                break;

            default:
                break;
        }

        return statsLevels;
    }

    private static void initStatsRacoutou(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                300,    //Upgrade price
                50.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0.,     //Speed
                10.,     //Range
                5.,     //Damage
                2.5,     //FreqAtk
        });

        statsLevels.add(new Object[]{
                "Lunettes de soleil",
                800,    //Upgrade price
                100.,     //Health   (+50)
                0.,      //Speed
                15.,      //Range   (+5)
                7.,      //Damage    (+2)
                2.3,    //FreqAtk (+.2)
        });

        statsLevels.add(new Object[]{
                "Cocktail",
                1500,    //Upgrade price
                200.,     //Health   (+100)
                0.,      //Speed
                15.,      //Range
                10.,     //Damage   (+3)
                2.,    //FreqAtk (+.3)
        });

        statsLevels.add(new Object[]{
                "La bouée",
                null,   //Upgrade price
                350.,     //Health   (+150)
                0.,      //Speed
                20.,     //Range     (+5)
                15.,     //Damage   (+5)
                1.5,      //FreqAtk (+.5)
        });
    }

    private static void initStatsChatClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                25,    //Upgrade price
                6.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
        });

        statsLevels.add(new Object[]{
                "Patte de bronze",
                75,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                7.,      //Range    (+2)
                7.,      //Damage    (+2)
                1,    //FreqAtk
        });

        statsLevels.add(new Object[]{
                "Patte d'argent",
                150,    //Upgrade price
                15.,     //Health   (+3)
                1.5,      //Speed   (+.5)
                8.,      //Range     (+1)
                7.,     //Damage
                1.2,    //FreqAtk (+.2)
        });

        statsLevels.add(new Object[]{
                "Patte d'or",
                null,   //Upgrade price
                15.,     //Health
                1.8,      //Speed   (+.3)
                8.,      //Range
                9.,     //Damage    (+2)
                1.5,    //FreqAtk (+.3)
        });
    }

    private static void initStatsPouletClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,     //Argent rapporté
                10.,     //Health
                1.,     //Speed
                5.,     //Range
                1.,     //Damage
                1.,     //FreqAtk
        });
    }

    private static void initStatsPouletBouclier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,     //Argent rapporté
                10.,     //Health
                .8,     //Speed
                4.,     //Range
                8.,     //Damage
                1.,     //FreqAtk
                5.      //Bouclier
        });
    }

    private static void initStatsChatCuisinier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0.,     //Speed
                5.,     //Range
                .5,     //Damage
                1.,     //FreqAtk
                20.,    //BuffRange
                1.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                150,    //Upgrade price
                12.,     //Health   (+4)
                0.,      //Speed
                7.,      //Range    (+2)
                .8,      //Damage    (+.3)
                1,    //FreqAtk
                20.,     //BuffRange
                1.,    //TpsBuff
                1.      //TpsRepo   (+.5)
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                300,    //Upgrade price
                20.,     //Health   (+8)
                0.,      //Speed
                7.,      //Range
                .8,      //Damage
                1,    //FreqAtk
                30.,     //BuffRange    (+10)
                1.2,    //TpsBuff        (+.2)
                1.      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "MOF",
                null,   //Upgrade price
                30.,     //Health   (+10)
                0.,      //Speed
                9.,      //Range    (+2)
                .8,      //Damage
                1.5,    //FreqAtk   (+.5)
                40.,     //BuffRange    (+10)
                1.7,    //TpsBuff        (+.5)
                1.2      //TpsRepo       (+.2)
        });
    }

    private static void initStatsChatMedecin(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                120,    //Upgrade price
                7.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                2.,     //Range
                .5,     //Damage
                .2,     //FreqAtk
                5.,    //BuffRange
                1.,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Trousse de secours",
                180,    //Upgrade price
                12.,     //Health   (+5)
                1.,      //Speed
                2.,      //Range
                .5,      //Damage
                .2,     //FreqAtk
                6.,    //BuffRange  (+1)
                1.5,     //TpsBuff  (.5)
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Giro",
                300,    //Upgrade price
                12.,     //Health
                1.5,      //Speed   (+.5)
                1.5,      //Range   (-.5)
                .5,      //Damage
                .5,     //FreqAtk   (+.3)
                6.,    //BuffRange
                1.5,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Scalpel d'or",
                null,   //Upgrade price
                20.,     //Health   (+8)
                1.8,      //Speed   (+.3)
                1.5,      //Range
                .5,      //Damage
                .5,     //FreqAtk
                10.,    //BuffRange  (+4)
                4,     //TpsBuff    (.5)
                .5     //TpsRepo
        });
    }

    private static void initStatsPouletConservateur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,     //Argent rapporté
                25.,     //Health
                1.1,     //Speed
                6.,     //Range
                5.,     //Damage
                1.2,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                4.     //TpsRepo
        });
    }

    private static void initStatsChatScientifique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                100,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                10.,    //BuffRange
                3.,     //TpsBuff
                .5,     //TpsRepo
                1.,      //DivForce
                .9      //DivVit
        });

        statsLevels.add(new Object[]{
                "Remède douteux",
                175,    //Upgrade price
                10.,     //Health   (+2)
                1.,     //Speed
                7.,     //Range     (+2)
                2.,     //Damage
                1.,     //FreqAtk
                10.,    //BuffRange
                3.,     //TpsBuff
                .5,     //TpsRepo
                1.,      //DivForce
                .7      //DivVit    (-.2)
        });

        statsLevels.add(new Object[]{
                "L'anti-Hulk",
                300,    //Upgrade price
                12.,     //Health   (+2)
                1.,     //Speed
                7.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                12.,    //BuffRange (+2)
                3.5,     //TpsBuff  (+.5)
                .5,     //TpsRepo
                .8,      //DivForce (-.2)
                .7      //DivVit
        });

        statsLevels.add(new Object[]{
                "Le Pr Raoult",     /// TODO : SVP je VEUX une image de Raoult pr l'image d'upgrade :pray:
                null,   //Upgrade price
                10.,     //Health   (-2)
                .8,     //Speed     (-.2)
                5.,     //Range     (-2)
                1.5,     //Damage   (-.5)
                .8,     //FreqAtk   (-.2)
                25.,    //BuffRange (+13)
                5.,     //TpsBuff  (+1.5)
                1.,     //TpsRepo   (+.5)
                .6,      //DivForce (-.2)
                .5      //DivVit    (-.2)
        });
    }

    private static void initStatsChatHypnotiseur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                100,    //Upgrade price
                6.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0.,     //Speed
                2.,     //Range
                1.,     //Damage
                1.,     //FreqAtk
                10.,    //BuffRange
                1.,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Créoles",
                200,    //Upgrade price
                8.,     //Health    (+2)
                0.,     //Speed
                2.,     //Range
                1.5,     //Damage   (+.5)
                1.,     //FreqAtk
                12.,    //BuffRange (+2)
                1.,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Boule de cristal",
                350,    //Upgrade price
                15.,     //Health   (+7)
                0.,     //Speed
                4.,     //Range     (+2)
                1.8,     //Damage   (+.3)
                1.,     //FreqAtk
                15.,    //BuffRange (+3)
                1.2,     //TpsBuff  (+.2)
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Troisième oeil",
                null,   //Upgrade price
                23.,     //Health   (+8)
                0.,     //Speed
                6.,     //Range     (+2)
                1.8,     //Damage
                1.2,     //FreqAtk  (+.2)
                20.,    //BuffRange (+5)
                1.5,     //TpsBuff  (+.3)
                1.     //TpsRepo
        });
    }

    private static void initStatsChatJournaliste(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                80,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                6.,      //TpsRepo
                1       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Pigiste",
                125,    //Upgrade price
                10.,     //Health   (+2)
                1.2,     //Speed    (+.2)
                5.,     //Range
                6.,     //Damage    (+1)
                1.1,     //FreqAtk  (+.1)
                15.,    //BuffRange
                3.,     //TpsBuff
                6.,      //TpsRepo
                1       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Reporterre novice",
                200,    //Upgrade price
                12.,     //Health   (+2)
                1.3,     //Speed    (+.1)
                6.,     //Range (+1)
                6.,     //Damage
                1.1,     //FreqAtk
                18.,    //BuffRange (+3)
                3.5,     //TpsBuff  (+.5)
                5.5,      //TpsRepo (-.5)
                2       //NbrVictimes   (+1)
        });

        statsLevels.add(new Object[]{
                "Reporterre aguerit",
                null,   //Upgrade price
                15.,     //Health   (+3)
                1.5,     //Speed    (+.2)
                7.,     //Range     (+1)
                7.,     //Damage    (+1)
                1.2,     //FreqAtk  (+.1)
                20.,    //BuffRange (+2)
                3.5,     //TpsBuff
                5.,      //TpsRepo  (-.5)
                3       //NbrVictimes   (+1)
        });
    }

    private static void initStatsPouletMenottes(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                50,     //Argent rapporté
                30.,     //Health
                1.2,     //Speed
                8.,     //Range
                10.,     //Damage
                1.,     //FreqAtk
                20.,    //BuffRange
                3.,     //TpsBuff
                5.,      //TpsRepo
                8       //NbrVictimes
        });
    }

    private static void initStatsPouletVolant(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                20,     //Argent rapporté
                8.,     //Health
                2.5,     //Speed
                3.,     //Range
                5.,     //Damage
                2.,     //FreqAtk
        });
    }

    /// TODO : PAS ENCORE FAIT
    private static void initStatsChienInterim(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 1",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 2",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 3",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }

    /// TODO : PAS ENCORE FAIT
    private static void initStatsPouletMineur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });
    }

    /// TODO : PAS ENCORE FAIT
    private static void initStatsTaupe(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Frontale",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Casque de mineur",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "La pelle",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }
}
