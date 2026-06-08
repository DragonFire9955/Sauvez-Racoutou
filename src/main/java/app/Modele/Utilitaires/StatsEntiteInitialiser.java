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
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                0.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                0.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                0.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }

    private static void initStatsChatClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
        });
    }

    private static void initStatsPouletClassique(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                1.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
        });
    }

    private static void initStatsPouletBouclier(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

    private static void initStatsChatCuisinier(List<Object[]> statsLevels) {

        statsLevels.add(new Object[]{
                "Default",
                70,    //Upgrade price
                8.,     //Health
                //Ici je met pas les coins car le prix de vente sera l'addition des prix d'upgrade + le getCoin()
                0.,     //Speed
                5.,     //Range
                5.,     //Damage
                1.,     //FreqAtk
                15.,    //BuffRange
                3.,     //TpsBuff
                .5     //TpsRepo
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                0.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                0.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                0.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5      //TpsRepo
        });
    }

    private static void initStatsChatMedecin(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

    private static void initStatsPouletConservateur(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

    private static void initStatsChatScientifique(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

    private static void initStatsChatHypnotiseur(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

    private static void initStatsChatJournaliste(List<Object[]> statsLevels) {

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
                .5,      //TpsRepo
                6       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5,      //TpsRepo
                3       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5,      //TpsRepo
                4       //NbrVictimes   (+1)
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5,      //TpsRepo
                6       //NbrVictimes   (+2)
        });
    }

    private static void initStatsPouletMenottes(List<Object[]> statsLevels) {

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
                .5,      //TpsRepo
                6       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "Spatule d'or",
                150,    //Upgrade price
                12.,     //Health   (+4)
                1.,      //Speed
                5.,      //Range
                7.,      //Damage    (+2)
                1.2,    //FreqAtk (+.2)
                15.,     //BuffRange
                3.5,    //TpsBuff (+.5)
                .5,      //TpsRepo
                3       //NbrVictimes
        });

        statsLevels.add(new Object[]{
                "BBQ modeste",
                300,    //Upgrade price
                20.,     //Health   (+8)
                2.,      //Speed
                8.,      //Range     (+3)
                10.,     //Damage   (+3)
                1.5,    //FreqAtk (+.3)
                20.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5,      //TpsRepo
                4       //NbrVictimes   (+1)
        });

        statsLevels.add(new Object[]{
                "licence VIP",
                null,   //Upgrade price
                30.,     //Health   (+10)
                3.,      //Speed
                10.,     //Range     (+2)
                15.,     //Damage   (+5)
                2.,      //FreqAtk (+.5)
                25.,     //BuffRange (+5)
                3.5,    //TpsBuff
                .5,      //TpsRepo
                6       //NbrVictimes   (+2)
        });
    }

    private static void initStatsPouletVolant(List<Object[]> statsLevels) {

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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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

        statsLevels.add(new Object[]{
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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
                "Spatule d'or",
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
                "BBQ modeste",
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
                "licence VIP",
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
