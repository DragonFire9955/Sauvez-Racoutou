package app.Modele.Utilitaires;

import app.Modele.Entites.Entite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class StatsEntiteInitialiser {

    public static List<Object[]> getStatsLevels(String name) {

        List<Object[]> statsLevels = new ArrayList<>();


        switch (name) {
            case "racoutou":
                initStatsRacoutou(statsLevels);
                break;
// CLASSIQUES
            case "chatClassique":
                initStatsChatClassique(statsLevels);
                break;
            case "pouletClassique":
                initStatsPouletClassique(statsLevels);
                break;
            case "pouletRolleur":
                initStatsPouletRolleur(statsLevels);
                break;
// SPECIALISES
            case "pouletBouclier":
                initStatsPouletBouclier(statsLevels);
                break;
            case "pouletProjectible":
                initStatsPouletProjectible(statsLevels);
                break;
    // BUFFER
            case "chatCuisinier":
                initStatsChatCuisinier(statsLevels);
                break;
            case "chatMedecin":
                initStatsChatMedecin(statsLevels);
                break;
            case "pouletConservateur":
                initStatsPouletConservateur(statsLevels);
                break;
    // DEBUFFER
            case "pouletIGPN":
                initPouletIGPN(statsLevels);
                break;
        // ALTERATIONS ELEMENTAIRES
            case "chatScientifique":
                initStatsChatScientifique(statsLevels);
                break;
            case "chatHypnotiseur":
                initStatsChatHypnotiseur(statsLevels);
                break;
        // STUNNER
            case "chatJournaliste":
                initStatsChatJournaliste(statsLevels);
                break;
            case "pouletMenottes":
                initStatsPouletMenottes(statsLevels);
                break;


            case "poubelle":
                initStatsPoubelle(statsLevels);
                break;
/*
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

 */

            default:
                break;
        }

        return statsLevels;
    }

    private static void initStatsRacoutou(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                3,    //Upgrade price
                500.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                5.,     //Damage
                2.5,     //FreqAtk
                0.,     //Speed

        });

        statsLevels.add(new Object[]{
                "Lunettes de soleil",
                8,    //Upgrade price
                1000.,     //Health   (+50)
                45.,      //Range   (+5)
                7.,      //Damage    (+2)
                2.3,    //FreqAtk (+.2)
                0.,      //Speed

        });

        statsLevels.add(new Object[]{
                "Cocktail",
                15,    //Upgrade price
                2000.,     //Health   (+100)
                45.,      //Range
                10.,     //Damage   (+3)
                2.,    //FreqAtk (+.3)
                0.,      //Speed

        });

        statsLevels.add(new Object[]{
                "La bouée",
                null,   //Upgrade price
                3500.,     //Health   (+150)
                50.,     //Range     (+5)
                40.,     //Damage   (+5)
                1.5,      //FreqAtk (+.5)
                0.,      //Speed
        });
    }

    private static void initStatsChatClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                25,    //Upgrade price
                6.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
        });

        statsLevels.add(new Object[]{
                "Patte de bronze",
                75,    //Upgrade price
                12.,     //Health   (+4)
                42.,      //Range    (+2)
                7.,      //Damage    (+2)
                1,    //FreqAtk
                1.,      //Speed
        });

        statsLevels.add(new Object[]{
                "Patte d'argent",
                150,    //Upgrade price
                15.,     //Health   (+3)
                43.,      //Range     (+1)
                7.,     //Damage
                1.2,    //FreqAtk (+.2)
                1.5,      //Speed   (+.5)
        });

        statsLevels.add(new Object[]{
                "Patte d'or",
                null,   //Upgrade price
                15.,     //Health
                43.,      //Range
                9.,     //Damage    (+2)
                1.5,    //FreqAtk (+.3)
                1.8,      //Speed   (+.3)
        });
    }

    private static void initStatsPouletClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                8,     //Argent rapporté
                10.,     //Health
                40.,     //Range
                1.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
        });
    }

    private static void initStatsPouletRolleur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                12,     //Argent rapporté
                7.,     //Health
                40.,     //Range
                1.,     //Damage
                .5,     //FreqAtk
                2.,     //Speed
        });
    }

    private static void initStatsPouletBouclier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                15,     //Argent rapporté
                10.,     //Health
                35.,     //Range
                8.,     //Damage
                1.,     //FreqAtk
                .8,     //Speed
                5.      //Bouclier
        });
    }

    private static void initStatsPouletProjectible(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                15,     //Argent rapporté
                10.,     //Health
                150.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                3.8,     //Speed
                100.,    //RangeEffect
                3.,     //tempsAction
                2.     //tempsRepo
        });
    }

    private static void initStatsChatCuisinier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                .5,     //Damage
                1.,     //FreqAtk
                0.,     //Speed
                150.,    //BuffRange
                1.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                150,    //Upgrade price
                12.,     //Health   (+4)
                42.,      //Range    (+2)
                .8,      //Damage    (+.3)
                1.,    //FreqAtk
                0.,      //Speed
                150.,     //BuffRange
                1.,    //TpsBuff
                1.      //TpsRepo   (+.5)
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                300,    //Upgrade price
                20.,     //Health   (+8)
                42.,      //Range
                .8,      //Damage
                1.,    //FreqAtk
                0.,      //Speed
                160.,     //BuffRange    (+10)
                1.2,    //TpsBuff        (+.2)
                1.      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "MOF",
                null,   //Upgrade price
                30.,     //Health   (+10)
                44.,      //Range    (+2)
                .8,      //Damage
                1.5,    //FreqAtk   (+.5)
                0.,      //Speed
                170.,     //BuffRange    (+10)
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
                40.,     //Range
                .5,     //Damage
                .2,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                1.,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Trousse de secours",
                180,    //Upgrade price
                12.,     //Health   (+5)
                40.,      //Range
                .5,      //Damage
                .2,     //FreqAtk
                1.,      //Speed
                151.,    //BuffRange  (+1)
                1.5,     //TpsBuff  (.5)
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Giro",
                300,    //Upgrade price
                12.,     //Health
                35.,      //Range   (-.5)
                .5,      //Damage
                .5,     //FreqAtk   (+.3)
                1.5,      //Speed   (+.5)
                151.,    //BuffRange
                1.5,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Scalpel d'or",
                null,   //Upgrade price
                20.,     //Health   (+8)
                35,      //Range
                .5,      //Damage
                .5,     //FreqAtk
                1.8,      //Speed   (+.3)
                155.,    //BuffRange  (+4)
                4,     //TpsBuff    (.5)
                .5     //TpsRepo
        });
    }

    private static void initStatsPouletConservateur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                30,     //Argent rapporté
                25.,     //Health
                40.,     //Range
                5.,     //Damage
                1.2,     //FreqAtk
                1.1,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                4.     //TpsRepo
        });
    }

    /// TODO : a faire, c un allié
    private static void initPouletIGPN(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                60,     //Argent rapporté
                25.,     //Health
                40.,     //Range
                5.,     //Damage
                1.2,     //FreqAtk
                1.1,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                5.,     //TpsRepo
                3,      //NbrVictimes
                .8,    //EffetForce
                .8,     //EffetVit
        });
    }

    private static void initStatsChatScientifique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                100,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5,     //TpsRepo
                2,      //NbrVictimes
                1.,      //DivForce
                .9      //DivVit
        });

        statsLevels.add(new Object[]{
                "Remède douteux",
                175,    //Upgrade price
                10.,     //Health   (+2)
                42.,     //Range     (+2)
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5,     //TpsRepo
                1,      //NbrVictimes
                1.,      //DivForce
                .7      //DivVit    (-.2)
        });

        statsLevels.add(new Object[]{
                "L'anti-Hulk",
                300,    //Upgrade price
                12.,     //Health   (+2)
                42.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                152.,    //BuffRange (+2)
                3.5,     //TpsBuff  (+.5)
                .5,     //TpsRepo
                2,      //NbrVictimes   (+1)
                .8,      //DivForce (-.2)
                .7      //DivVit
        });

        statsLevels.add(new Object[]{
                "Le Pr Raoult",     /// TODO : SVP je VEUX une image de Raoult pr l'image d'upgrade :pray:
                null,   //Upgrade price
                10.,     //Health   (-2)
                40.,     //Range     (-2)
                1.5,     //Damage   (-.5)
                .8,     //FreqAtk   (-.2)
                .8,     //Speed     (-.2)
                160.,    //BuffRange (+13)
                5.,     //TpsBuff  (+1.5)
                1.,     //TpsRepo   (+.5)
                3,      //NbrVictimes  (+1)
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
                40.,     //Range
                1.,     //Damage
                1.,     //FreqAtk
                0.,     //Speed
                1.8,    //DmgSpe
                1.5,     //freqAtkSpeciale  (+.3)
                400.     //rangeSpe
        });

        statsLevels.add(new Object[]{
                "Créoles",
                200,    //Upgrade price
                8.,     //Health    (+2)
                40.,     //Range
                1.5,     //Damage   (+.5)
                1.,     //FreqAtk
                0.,     //Speed
                3.6,    //DmgSpe (+1.8)
                1.5,     //freqAtkSpeciale
                160.     //rangeSpe  (+10)
        });

        statsLevels.add(new Object[]{
                "Boule de cristal",
                350,    //Upgrade price
                16.,     //Health   (+8)
                41.,     //Range     (+1)
                1.5,     //Damage
                1.2,     //FreqAtk  (+.2)
                0.,     //Speed
                5,    //DmgSpe (+1.4)
                1.7,     //freqAtkSpeciale  (+.2)
                165.     //rangeSpe  (+5)
        });

        statsLevels.add(new Object[]{
                "Troisième oeil",
                null,   //Upgrade price
                25.,     //Health   (+9)
                41.,     //Range
                1.5,     //Damage
                1.5,     //FreqAtk  (+.5)
                0.,     //Speed
                7,    //DmgSpe (+2)
                2.,     //freqAtkSpeciale  (+.3)
                170.     //rangeSpe  (+5)
        });
    }

    private static void initStatsChatJournaliste(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                80,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                6.,      //TpsRepo
                1       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Pigiste",
                125,    //Upgrade price
                10.,     //Health   (+2)
                40.,     //Range
                6.,     //Damage    (+1)
                1.1,     //FreqAtk  (+.1)
                1.2,     //Speed    (+.2)
                150.,    //BuffRange
                3.,     //TpsBuff
                6.,      //TpsRepo
                1       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Reporterre novice",
                200,    //Upgrade price
                12.,     //Health   (+2)
                41.,     //Range (+1)
                6.,     //Damage
                1.1,     //FreqAtk
                1.3,     //Speed    (+.1)
                153.,    //BuffRange (+3)
                3.5,     //TpsBuff  (+.5)
                5.5,      //TpsRepo (-.5)
                2       //NbrVictimes   (+1)
        });

        statsLevels.add(new Object[]{
                "Reporterre aguerit",
                null,   //Upgrade price
                15.,     //Health   (+3)
                42.,     //Range     (+1)
                7.,     //Damage    (+1)
                1.2,     //FreqAtk  (+.1)
                1.5,     //Speed    (+.2)
                155.,    //BuffRange (+2)
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
                40.,     //Range
                10.,     //Damage
                1.,     //FreqAtk
                1.2,     //Speed
                150.,    //BuffRange
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
                40.,     //Range
                5.,     //Damage
                2.,     //FreqAtk
                2.5     //Speed
        });
    }

    /// TODO : PAS ENCORE FAIT
    private static void initStatsChienInterim(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 1",
                150,    //Upgrade price
                12.,     //Health   (+4)
                40.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                1.,      //Speed
                150.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 2",
                300,    //Upgrade price
                20.,     //Health   (+8)
                43.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                2.,      //Speed
                155.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Personnalisé 3",
                null,   //Upgrade price
                30.,     //Health   (+10)
                45.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                3.,      //Speed
                160.,     //BuffRange (+5)
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
                40.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
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
                40.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Frontale",
                150,    //Upgrade price
                12.,     //Health   (+4)
                40.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                1.,      //Speed
                150.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Casque de mineur",
                300,    //Upgrade price
                20.,     //Health   (+8)
                43.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                2.,      //Speed
                155.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "La pelle",
                null,   //Upgrade price
                30.,     //Health   (+10)
                45.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                3.,      //Speed
                160.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }

    /// TODO : PAS ENCORE FAIT
    private static void initStatsPoubelle(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Frontale",
                150,    //Upgrade price
                12.,     //Health   (+4)
                40.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                1.,      //Speed
                150.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Casque de mineur",
                300,    //Upgrade price
                20.,     //Health   (+8)
                43.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                2.,      //Speed
                155.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "La pelle",
                null,   //Upgrade price
                30.,     //Health   (+10)
                45.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                3.,      //Speed
                160.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }
}
