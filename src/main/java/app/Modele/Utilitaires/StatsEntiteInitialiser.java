package app.Modele.Utilitaires;

import app.Modele.Entites.Entite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class  StatsEntiteInitialiser {

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
            case "pouletRoller":
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
            case "ruchien":
                initStatsRuchien(statsLevels);
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
                100,    //Upgrade price
                500.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                5.,     //Damage
                2.5,     //FreqAtk
                0.,     //Speed

        });

        statsLevels.add(new Object[]{
                "Lunettes de soleil",
                250,    //Upgrade price
                1200.,     //Health   (+50)
                45.,      //Range   (+5)
                10.,      //Damage    (+2)
                2.3,    //FreqAtk (+.2)
                0.,      //Speed

        });

        statsLevels.add(new Object[]{
                "Cocktail",
                500,    //Upgrade price
                2500,     //Health   (+100)
                45.,      //Range
                15.,     //Damage   (+3)
                2.,    //FreqAtk (+.3)
                0.,      //Speed

        });

        statsLevels.add(new Object[]{
                "La bouée",
                null,   //Upgrade price
                4000.,     //Health   (+150)
                50.,     //Range     (+5)
                20.,     //Damage   (+5)
                1.5,      //FreqAtk (+.5)
                0.,      //Speed
        });
    }

    private static void initStatsChatClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                25,    //Upgrade price
                20.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
        });

        statsLevels.add(new Object[]{
                "Patte de bronze",
                75,    //Upgrade price
                40.,     //Health   (+4)
                42.,      //Range    (+2)
                5.,      //Damage    (+2)
                0.9,    //FreqAtk
                1.,      //Speed
        });

        statsLevels.add(new Object[]{
                "Patte d'argent",
                150,    //Upgrade price
                60.,     //Health   (+3)
                45.,      //Range     (+1)
                7.,     //Damage
                0.8,    //FreqAtk (+.2)
                1.5,      //Speed   (+.5)
        });

        statsLevels.add(new Object[]{
                "Patte d'or",
                null,   //Upgrade price
                80.,     //Health
                50.,      //Range
                10.,     //Damage    (+2)
                0.7,    //FreqAtk (+.3)
                1.8,      //Speed   (+.3)
        });
    }

    private static void initStatsPouletClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                13,     //Argent rapporté
                15.,     //Health
                40.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
        });
    }

    private static void initStatsPouletRolleur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                18,     //Argent rapporté
                7.,     //Health
                40.,     //Range
                2.,     //Damage
                1.,     //FreqAtk
                2.,     //Speed
        });
    }

    private static void initStatsPouletBouclier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                30,     //Argent rapporté
                30.,     //Health
                35.,     //Range
                1.,     //Damage
                1.,     //FreqAtk
                .7,     //Speed
                15.      //Bouclier
        });
    }

    private static void initStatsPouletProjectible(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                25,     //Argent rapporté
                25.,     //Health
                150.,     //Range
                3.,     //Damage
                1.,     //FreqAtk
                1.2,     //Speed
                100.,    //RangeEffect
                3.,     //tempsAction
                2.     //tempsRepo
        });
    }

    private static void initStatsChatCuisinier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                30.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                .5,     //Damage
                1.2,     //FreqAtk
                0.,     //Speed
                120.,    //BuffRange
                1.,     //TpsBuff
                2.5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                150,    //Upgrade price
                50.,     //Health   (+4)
                42.,      //Range    (+2)
                .8,      //Damage    (+.3)
                1.,    //FreqAtk
                0.,      //Speed
                140.,     //BuffRange
                1.3,    //TpsBuff
                2.      //TpsRepo   (+.5)
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                300,    //Upgrade price
                80.,     //Health   (+8)
                42.,      //Range
                1.,      //Damage
                0.9,    //FreqAtk
                0.,      //Speed
                160.,     //BuffRange    (+10)
                1.6,    //TpsBuff        (+.2)
                1.      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "MOF",
                null,   //Upgrade price
                160.,     //Health   (+10)
                44.,      //Range    (+2)
                1.2,      //Damage
                0.8,    //FreqAtk   (+.5)
                0.,      //Speed
                170.,     //BuffRange    (+10)
                2.,    //TpsBuff        (+.5)
                1.2      //TpsRepo       (+.2)
        });
    }

    private static void initStatsChatMedecin(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                180,    //Upgrade price
                25.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                .2,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                120.,    //BuffRange
                .1,     //TpsBuff
                2.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Trousse de secours",
                250,    //Upgrade price
                45.,     //Health   (+5)
                40.,      //Range
                .5,      //Damage
                .2,     //FreqAtk
                1.,      //Speed
                150.,    //BuffRange  (+1)
                1.5,     //TpsBuff  (.5)
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Giro",
                320,    //Upgrade price
                65.,     //Health
                45.,      //Range   (-.5)
                .5,      //Damage
                .5,     //FreqAtk   (+.3)
                1.5,      //Speed   (+.5)
                170.,    //BuffRange
                1.5,     //TpsBuff
                1.     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Scalpel d'or",
                null,   //Upgrade price
                80.,     //Health   (+8)
                50.,      //Range
                .5,      //Damage
                .5,     //FreqAtk
                1.8,      //Speed   (+.3)
                190.,    //BuffRange  (+4)
                4,     //TpsBuff    (.5)
                .5     //TpsRepo
        });
    }

    private static void initStatsPouletConservateur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                30,     //Argent rapporté
                40.,     //Health
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
                35.,     //Health
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

        statsLevels.add(new Object[]{
                "Brigadier",
                120,     //Upgrade
                35.,     //Health
                42.,     //Range
                6.,     //Damage
                1.1,     //FreqAtk
                1.1,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                4.5,     //TpsRepo
                3,      //NbrVictimes
                .7,    //EffetForce
                .7,     //EffetVit
        });

        statsLevels.add(new Object[]{
                "Commissaire",
                250,     //Upgrade
                50.,     //Health
                45.,     //Range
                8.,     //Damage
                1.0,     //FreqAtk
                1.2,     //Speed
                160.,    //BuffRange
                3.5,     //TpsBuff
                4.,     //TpsRepo
                4,      //NbrVictimes
                .6,    //EffetForce
                .6,     //EffetVit
        });

        statsLevels.add(new Object[]{
                "Préfet",
                null,     //Upgrade
                70.,     //Health
                50.,     //Range
                7.,     //Damage
                0.9,     //FreqAtk
                1.3,     //Speed
                170.,    //BuffRange
                4.,     //TpsBuff
                3.,     //TpsRepo
                5,      //NbrVictimes
                .5,    //EffetForce
                .5,     //EffetVit
        });
    }

    private static void initStatsRuchien(List<Object[]> statsLevels) {
        statsLevels.add(new Object[]{
                "Default",
                300,     //Argent rapporté
                100.,    //Health
                500.,    //Range
                0.,      //Damage
                0.,      //FreqAtk
                2.0,     //Speed
                500.,   //BuffRange
                0.,      //TpsBuff
                0.,      //TpsRepo
                0,       //NbrVictimes
                5.     //tempsRequisPalier
        });
    }

    private static void initStatsChatScientifique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                100,    //Upgrade price
                35.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                2.,     //Damage
                1.2,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                .5,     //TpsRepo
                2,      //NbrVictimes
                1.,      //DivForce
                .85      //DivVit
        });

        statsLevels.add(new Object[]{
                "Remède douteux",
                200,    //Upgrade price
                55.,     //Health   (+2)
                42.,     //Range     (+2)
                3.,     //Damage
                1.1,     //FreqAtk
                1.,     //Speed
                150.,    //BuffRange
                3.5,     //TpsBuff
                .5,     //TpsRepo
                1,      //NbrVictimes
                .9,      //DivForce
                .75      //DivVit    (-.2)
        });

        statsLevels.add(new Object[]{
                "L'anti-Hulk",
                350,    //Upgrade price
                80.,     //Health   (+2)
                45.,     //Range
                4.,     //Damage
                1.,     //FreqAtk
                1.,     //Speed
                160.,    //BuffRange (+2)
                4.,     //TpsBuff  (+.5)
                .5,     //TpsRepo
                2,      //NbrVictimes   (+1)
                .75,      //DivForce (-.2)
                .6      //DivVit
        });

        statsLevels.add(new Object[]{
                "Le Pr Raoult",     /// TODO : SVP je VEUX une image de Raoult pr l'image d'upgrade :pray:
                null,   //Upgrade price
                50.,     //Health   (-2)
                40.,     //Range     (-2)
                1.5,     //Damage   (-.5)
                .8,     //FreqAtk   (-.2)
                .8,     //Speed     (-.2)
                200.,    //BuffRange (+13)
                5.,     //TpsBuff  (+1.5)
                1.,     //TpsRepo   (+.5)
                3,      //NbrVictimes  (+1)
                .5,      //DivForce (-.2)
                .5      //DivVit    (-.2)
        });
    }

    private static void initStatsChatHypnotiseur(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                100,    //Upgrade price
                10.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                0.,     //Damage
                1.,     //FreqAtk
                0.,     //Speed
                6.0,    //DmgSpe
                1.5,     //freqAtkSpeciale  (+.3)
                150.     //rangeSpe
        });

        statsLevels.add(new Object[]{
                "Créoles",
                200,    //Upgrade price
                20.,     //Health    (+2)
                40.,     //Range
                0.,     //Damage   (+.5)
                1.,     //FreqAtk
                0.,     //Speed
                12.0,    //DmgSpe (+1.8)
                1.5,     //freqAtkSpeciale
                160.     //rangeSpe  (+10)
        });

        statsLevels.add(new Object[]{
                "Boule de cristal",
                350,    //Upgrade price
                30.,     //Health   (+8)
                42.,     //Range     (+1)
                0.,     //Damage
                1.2,     //FreqAtk  (+.2)
                0.,     //Speed
                20.0,    //DmgSpe (+1.4)
                1.7,     //freqAtkSpeciale  (+.2)
                170.     //rangeSpe  (+5)
        });

        statsLevels.add(new Object[]{
                "Troisième oeil",
                null,   //Upgrade price
                50.,     //Health   (+9)
                45.,     //Range
                0.,     //Damage
                1.5,     //FreqAtk  (+.5)
                0.,     //Speed
                35.0,    //DmgSpe (+2)
                2.,     //freqAtkSpeciale  (+.3)
                180.     //rangeSpe  (+5)
        });
    }

    private static void initStatsChatJournaliste(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                80,    //Upgrade price
                30.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                40.,     //Range
                2.,     //Damage
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
                50.,     //Health   (+2)
                40.,     //Range
                12.,     //Damage    (+1)
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
                70.,     //Health   (+2)
                41.,     //Range (+1)
                16.,     //Damage
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
                95.,     //Health   (+3)
                42.,     //Range     (+1)
                22.,     //Damage    (+1)
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
                60.,     //Health
                40.,     //Range
                12.,     //Damage
                1.,     //FreqAtk
                1.2,     //Speed
                150.,    //BuffRange
                3.,     //TpsBuff
                5.,      //TpsRepo
                5       //NbrVictimes
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

    private static void initStatsPoubelle(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                35,    //Upgrade price
                150.,     //Health
                0.,     //Range
                0.,     //Damage
                0.,     //FreqAtk
                1,     //taille
                2,      //idPoids
                100,    //id
        });

        statsLevels.add(new Object[]{
                "Conteneur",
                70,    //Upgrade price
                350.,     //Health   (+12)
                0.,     //Range
                0.,     //Damage
                0.,     //FreqAtk
                1,     //taille
                3,      //idPoids
                101,    //id
        });

        statsLevels.add(new Object[]{
                "Voiture",
                120,    //Upgrade price
                750.,     //Health   (+30)
                0.,     //Range    (+20)
                0.,     //Damage
                0.,     //FreqAtk
                2,     //taille
                4,      //idPoids
                102,    //id
        });

        statsLevels.add(new Object[]{
                "Bus",
                200,    //Upgrade price
                1200.,     //Health   (+30)
                0.,     //Range    (+20)
                0.,     //Damage
                0.,     //FreqAtk
                2,     //taille
                5,      //idPoids
                103,    //id
        });
    }
}
