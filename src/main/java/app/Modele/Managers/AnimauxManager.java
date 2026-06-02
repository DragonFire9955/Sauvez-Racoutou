package app.Modele.Managers;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.Utilitaire;

import java.util.Map;

public class AnimauxManager {

    public static GameWorld w;

    public static Animal creerChatClassique(GameWorld w) {
        return new Animal(EnnemisSpawn.randomCoord(w), 5, 10, 2, 5, 1, 1, w, true);
    }

    public static Animal creerPouletClassique(GameWorld w) {
        return new Animal(EnnemisSpawn.randomCoord(w), 5, 10, 2, 5, 1, 1, w, false);
    }

/*
    public static void deplacementEnnemi(Animal a) {
        System.out.println("dans fonction");

        GameWorld w =a.getWorld();
        Entite racoutou = w.getRacoutou();
        Map<Double, Noeud> dijkstra = w.getDijkRacoutou();

        int[] tile= a.getTile();
        System.out.println("tile"+a.getTile()[0]+" "+ a.getTile()[1]);
        System.out.println(DeplacementDijkstra.coordToDouble(tile));

        //La tile n'existe pas
        if(!dijkstra.containsKey(DeplacementDijkstra.coordToDouble(tile))){
            System.out.println(dijkstra.get(DeplacementDijkstra.coordToDouble(tile)));
            System.out.println("tile non valable");
        }
        //L'ennemi est sur la tile de Racoutou
        else if (dijkstra.get(DeplacementDijkstra.coordToDouble(tile)).getParent()==null)
            System.out.println("racoutout");

        else {
            System.out.println("distance centre: "+Utilitaire.distance(a.getX(), a.getY(), centreTile(w, tile)[0], centreTile(w, tile)[1]) );
            System.out.println("centre tile: "+ centreTile(w, tile)[0]+ "  "+ centreTile(w, tile)[1]);
            if (tile[0] == racoutou.getTile()[0] && tile[1] == racoutou.getTile()[1]
                && Utilitaire.distance(a.getX(), a.getY(), centreTile(w, tile)[0], centreTile(w, tile)[1]) < 2) {
                return;
            }
            while(Utilitaire.distance(a.getX(), a.getY(), centreTile(w, tile)[0], centreTile(w, tile)[1]) < 2
                  ||  (tile[0] != racoutou.getTile()[0] && tile[1] != racoutou.getTile()[1])){
                tile = dijkstra.get(DeplacementDijkstra.coordToDouble(tile)).getParent().getCoord();
            }
            System.out.println("centre tile: "+ centreTile(w, tile)[0]+ "  "+ centreTile(w, tile)[1]);
            double dx = centreTile(w, tile)[1] - a.getX();

            double dy = centreTile(w, tile)[0] - a.getY();

            double dist = Math.sqrt(dx * dx + dy * dy);
            System.out.println("dx: "+ dx);

            dx /= dist;
            System.out.println("dx: "+ dx);
            dy /= dist;
            System.out.println();



            a.setX(a.getX() + dx * a.getVitesse());
            a.setY(a.getY() + dy * a.getVitesse());

            System.out.println(a.getX()+ "," + a.getY());
        }
    }

 */

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


        //La tile n'existe pas
        if(!dijkstra.containsKey(dir)){
            //System.out.println(dir);
            //System.out.println(dijkstra.get(DeplacementDijkstra.coordToDouble(tile)));
            //System.out.println("tile non valable:");
        }
        else {
            //L'ennemi va sur la tile de Racoutou
            if (dijkstra.get(dir) == null) {

                cible= racoutou.getCoord();
                //Si déjà proche de racoutou: stop
                if (Utilitaire.distance(a.getX(), a.getY(), racoutou.getX(), racoutou.getY()) < 2)
                    return;

            }

            else{
                //tile vers laquelle il se dirige
                tile = new int[]{dir.getI(), dir.getJ()};
                //centre de la tile direction
                cible= centreTile(w, tile);
                //tile suivante dir
                int[] tileSuiv= new int[]{dijkstra.get(dir).getI(), dijkstra.get(dir).getJ()};
                //centre de la tile suivante
                double[] cibleSuiv= centreTile(w, tileSuiv);
                //distance(p, tile)<distance(p, tileSuivante) => utilise tile suivante (evite tremblements)

                /*if (Utilitaire.distance(cible[0], cible[1], a.getX(), a.getY())
                  < Utilitaire.distance(cibleSuiv[0], cibleSuiv[1], a.getX(), a.getY()))
                    cible=cibleSuiv;

                 */
                if (Utilitaire.distance(cible[0], cible[1], a.getX(), a.getY()) < 2)
                    cible=cibleSuiv;
            }

            //System.out.println("centre tile: "+ centreTile(w, tile)[0]+ "  "+ centreTile(w, tile)[1]);
            double dist = Utilitaire.distance(cible[0], cible[1], a.getX(), a.getY());

            double dx = cible[0] - a.getX();

            double dy = cible[1] - a.getY();


                    //Math.sqrt(dx * dx + dy * dy);
            //System.out.println("dx: " + dx);

            dx /= dist;
            //System.out.println("dx: " + dx);
            dy /= dist;
            //System.out.println();


            a.setX(a.getX() + dx * a.getVitesse());
            a.setY(a.getY() + dy * a.getVitesse());

            //System.out.println(a.getX() + "," + a.getY());

        }
    }




}