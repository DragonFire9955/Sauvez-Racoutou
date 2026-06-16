package app.Modele.Managers;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.Buffer;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Ruchien;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.Stunner;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Barrage.Poubelle;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Modele.Utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Map;

public class EntitesManager {

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
        return new Animal("pouletRoller", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("pouletRoller"), false);
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
        return creerChatCuisinier(new double[]{w.getRacoutou().getX()-20, w.getRacoutou().getY()+20}, w);
    }

    public static Animal creerChatCuisinier(double[] coord, GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(0.25);
        return new Buffer ("chatCuisinier", coord , w, StatsEntiteInitialiser.getStatsLevels("chatCuisinier"), true, lbuff);
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

        if(dx*attaquant.getDirX()>0) {
            System.out.println("ALLLIE INVERS");
            attaquant.inverseDirX();
        }
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
            if (Utilitaire.distance(a.getX(), a.getY(), racoutou.getX(), racoutou.getY()) < (a.getRange()*.9))
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

        if(dx*a.getDirX()>0) {
            System.out.println("ENNEMI INVERS");
            a.inverseDirX();
        }
    }
    
    
    public static void creerEntite(String nom, double[] coord, GameWorld w) {
        Entite e;
        switch (nom) {
// CLASSIQUES
            case "chatClassique":
                e = creerChatClassique(coord, w);
                break;
            case "pouletClassique":
                e = creerPouletClassique(w);
                break;

// SPECIALISES
            case "pouletBouclier":
                e = new PouletBouclier(w);
                break;
            // BUFFER
            case "chatCuisinier":
                e =  creerChatCuisinier(coord, w);
                break;
            case "chatMedecin":
                e = creerChatMedecin(coord, w);
                break;
            case "pouletConservateur":
                e = creerPouletConservateur(w);
                break;
            // DEBUFFER
            case "pouletIGPN":
                e = new PouletIGPN(coord, w);
                break;
            case "ruchien":
                e = new Ruchien(coord, w);
                break;
            // ALTERATIONS ELEMENTAIRES
            case "chatScientifique":
                e = new ChatScientifique(coord, w);
                break;
            case "chatHypnotiseur":
                e = new ChatHypnotiseur(coord, w);
                break;
            // STUNNER
            case "chatJournaliste":
                e = creerChatJournaliste(coord, w);
                break;
            case "pouletMenottes":
                e = creerPouletMenotte(w);
                break;

            case "poubelle":
                e = new Poubelle(coord, w);
                break;

            default:
                System.out.println("Entite inconnue");
                e = null;
        }


        if(nom.equals("poubelle")) {
            w.ajouterBarrage((Barrage) e);
        }
        else
            w.ajouterAnimal( (Animal) e);
    }

    public static double[] coordRacoutou(GameWorld w){
        boolean trouve = false;
        int ligne = 0, col = 0;
        while(!trouve && ligne < w.getMap().length) {
            col = 0;
            while (!trouve && col < w.getMap()[ligne].length) {
                if (w.getMap()[ligne][col] == 11)
                    trouve = true;
                col++;
            }
            ligne++;
        }
        return new double[]{ (col+1.5)*w.getTailleTile(), (ligne+1.5)*w.getTailleTile()};
    }
}