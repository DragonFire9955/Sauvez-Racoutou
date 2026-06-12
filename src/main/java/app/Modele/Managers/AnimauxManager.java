package app.Modele.Managers;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.Stunner;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Map;

public class AnimauxManager {

    public static GameWorld w;


    // CLASSIQUE
    public static Animal creerChatClassique(GameWorld w) {
        return creerChatClassique(EnnemisSpawn.randomCoord(w), w);
    }

    public static Animal creerChatClassique(double[] coords, GameWorld w) {
        return new Animal("chatClassique", coords, w, StatsEntiteInitialiser.getStatsLevels("chatClassique"), true);
    }

    public static Animal creerPouletClassique(GameWorld w) {
        return new Animal("pouletClassique", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("pouletClassique"), false);
    }

    public static Animal creerPouletRolleur(GameWorld w) {
        return new Animal("pouletRolleur", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("pouletRolleur"), false);
    }

    // STUNNER
    public static Animal creerPouletMenotte(GameWorld w){
        return new Stunner("pouletMenottes", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("pouletMenottes"), false);
    }

    public static Animal creerChatJournaliste(GameWorld w){
        return creerChatJournaliste(EnnemisSpawn.randomCoord(w), w);
    }

    public static Animal creerChatJournaliste(double[] coords, GameWorld w){
        return new Stunner("chatJournaliste", coords, w, StatsEntiteInitialiser.getStatsLevels("chatJournaliste"), true);
    }

    // BUFFER
    public static Animal creerChatMedecin(GameWorld w) {
        return creerChatMedecin(EnnemisSpawn.randomCoord(w), w);
    }

    public static Animal creerChatMedecin(double[] coords, GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(1.);
        return new Buffer("chatMedecin", coords, w, StatsEntiteInitialiser.getStatsLevels("chatMedecin"), true, lbuff);
    }

    public static Animal creerChatCuisinier(GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(0.25);
        return new Buffer ("chatCuisinier", new double[]{w.getRacoutou().getX()-20, w.getRacoutou().getY()+20} , w, StatsEntiteInitialiser.getStatsLevels("chatCuisinier"), true, lbuff);
    }

    public static Animal creerPouletConservateur(GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(0.5);
        return new Buffer ("pouletConservateur", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("pouletConservateur"), false, lbuff);
    }



    public static double[] centreTile(GameWorld w, int[] coord) {
        return new double[]{ coord[1] * w.getTailleTile() + ((double) w.getTailleTile() / 2),
                coord[0] * w.getTailleTile() + ((double) w.getTailleTile() / 2) };
    }


    public static void deplacementAllie(Animal attaquant, Animal etoileDuNord){

        GameWorld w = attaquant.getWorld();
        DeplacementDijkstra dijk = new DeplacementDijkstra(w.getTailleTile(), w.getMap());
        //map <clef= Noeud, value= Predecesseur>
        Map<Noeud, Noeud> dijkstra = dijk.dijkstraCible(etoileDuNord.getCoord(), attaquant.getCoord());

        //direction = noeud suivant
        Noeud dir = dijkstra.get(new Noeud(attaquant.getTile()[0], attaquant.getTile()[1]));
        double[] cible;
        int[] tile;


        //Dejà sur la tuile actuelle ou prochaine tuile = racoutout
        if(!dijkstra.containsKey(dir)){
            cible= etoileDuNord.getCoord();
            //Si déjà proche de racoutou: stop
            if (Utilitaire.distance(attaquant.getX(), attaquant.getY(), etoileDuNord.getX(), etoileDuNord.getY()) < 2)
                return;
        }
        else if(dijkstra.get(dir) ==null) { //prochaine tuile = racoutou
            cible= etoileDuNord.getCoord();
        }
        else {
            //tile vers laquelle il se dirige
            tile = new int[]{dir.getI(), dir.getJ()};
            //centre de la tile direction
            cible= centreTile(w, tile);
            //tile suivante dir
            int[] tileSuiv= new int[]{dijkstra.get(dir).getI(), dijkstra.get(dir).getJ()};
            //centre de la tile suivante
            double[] cibleSuiv= centreTile(w, tileSuiv);
            if (Utilitaire.distance(cible[0], cible[1], attaquant.getX(), attaquant.getY()) < 2)
                cible=cibleSuiv;
        }

        double dist = Utilitaire.distance(cible[0], cible[1], attaquant.getX(), attaquant.getY());
        double dx = cible[0] - attaquant.getX();
        double dy = cible[1] - attaquant.getY();

        dx /= dist;
        dy /= dist;

        attaquant.setX(attaquant.getX() + dx * attaquant.getVitesse());
        attaquant.setY(attaquant.getY() + dy * attaquant.getVitesse());

    }

    public static void deplacementEnnemi2(Animal a) {

        GameWorld w =a.getWorld();
        Entite racoutou = w.getRacoutou();

        //map <clef= Noeud, value= Predecesseur>
        Map<Noeud, Noeud> dijkstra = w.getDijkRacoutou2();

        //direction = noeud suivant
        Noeud dir = dijkstra.get(new Noeud(a.getTile()[0], a.getTile()[1]));
        double[] cible;
        int[] tile;


        //Dejà sur la tuile actuelle ou prochaine tuile = racoutout
        if(!dijkstra.containsKey(dir)){
            cible= racoutou.getCoord();
            //Si déjà proche de racoutou: stop
            if (Utilitaire.distance(a.getX(), a.getY(), racoutou.getX(), racoutou.getY()) < 2)
                return;
        }
        else if(dijkstra.get(dir) ==null) { //prochaine tuile = racoutou
            cible= racoutou.getCoord();
        }
        else {
            //tile vers laquelle il se dirige
            tile = new int[]{dir.getI(), dir.getJ()};
            //centre de la tile direction
            cible= centreTile(w, tile);
            //tile suivante dir
            int[] tileSuiv= new int[]{dijkstra.get(dir).getI(), dijkstra.get(dir).getJ()};
            //centre de la tile suivante
            double[] cibleSuiv= centreTile(w, tileSuiv);
            if (Utilitaire.distance(cible[0], cible[1], a.getX(), a.getY()) < 2)
                cible=cibleSuiv;
        }

        double dist = Utilitaire.distance(cible[0], cible[1], a.getX(), a.getY());
        double dx = cible[0] - a.getX();
        double dy = cible[1] - a.getY();

        dx /= dist;
        dy /= dist;

        a.setX(a.getX() + dx * a.getVitesse());
        a.setY(a.getY() + dy * a.getVitesse());
    }




}