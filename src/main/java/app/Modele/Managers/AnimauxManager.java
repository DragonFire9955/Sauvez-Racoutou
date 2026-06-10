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
        return new Animal("Chat classique", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Chat classique"), true);
    }

    public static Animal creerPouletClassique(GameWorld w) {
        return new Animal("Poulet classique", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Poulet classique"), false);
    }

    public static Animal creerPouletRolleur(GameWorld w) {
        return new Animal("Poulet rolleur", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Poulet rolleur"), false);
    }

// STUNNER
    public static Animal creerPouletMenotte(GameWorld w){
        return new Stunner("Poulet menottes", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Poulet menottes"), false);
    }

    public static Animal creerChatJournaliste(GameWorld w){
        return new Stunner("Chat journaliste", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Chat journaliste"), true);
    }

// BUFFER
    public static Animal creerChatMedecin(GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(1.);
        return new Buffer("Chat médecin", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Chat médecin"), true, lbuff);
    }

    public static Animal creerChatCuisinier(GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(0.25);
        return new Buffer ("chatCuisinier", new double[]{w.getRacoutou().getX()-20, w.getRacoutou().getY()+20} , w, StatsEntiteInitialiser.getStatsLevels("chatCuisinier"), true, lbuff);
    }

    public static Animal creerPouletConservateur(GameWorld w) {
        ArrayList<Double> lbuff = new ArrayList<>();
        lbuff.add(0.5);
        return new Buffer ("Poulet conservateur", EnnemisSpawn.randomCoord(w), w, StatsEntiteInitialiser.getStatsLevels("Poulet conservateur"), false, lbuff);
    }



    public static double[] centreTile(GameWorld w, int[] coord) {
        return new double[]{ coord[1] * w.getTailleTile() + ((double) w.getTailleTile() / 2),
                coord[0] * w.getTailleTile() + ((double) w.getTailleTile() / 2) };
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
