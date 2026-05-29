package app.Modele.Chemins;

import app.Modele.Chemins.DeplacementMethodes;
import app.Modele.GameWorld;
import app.Modele.Utilitaires.Noeud;
import app.Modele.Utilitaires.Utilitaire;

import java.util.*;

public class DeplacementDijkstra {

    private GameWorld w;
    private int[][] map;
    private int lignes;
    private int colonnes;
/*
    public DeplacementDijkstra(int[][] map) {

        this.map = map;
        this.lignes = map.length;
        this.colonnes = map[0].length;
    }

 */

    public DeplacementDijkstra(GameWorld w) {

        this.w=w;
        this.map=w.getMap();
        this.lignes = map.length;
        this.colonnes = map[0].length;
    }

    //Ajout du ptn de départ
    public Map<Noeud, Integer> calculerDistances(int startX, int startY) {

        Noeud dep = new Noeud(startX, startY);

        //On crée une liste qui trie pour le meilleur getCoutEstimDepArr (plus petit -> plus grand)
        PriorityQueue<Noeud> listNoeudsPasVu = new PriorityQueue<>(Comparator.comparingInt(Noeud::getCoutDepuisDeprt));

        //Recevra touts les noeuds passés
        Set<Noeud> listNoeudsVu = new HashSet<>();

        //Pr chq noeud, le meilleur coût
        Map<Noeud, Integer> meilleurCout = new HashMap<>();

        //Init
        dep.setCoutDepuisDeprt(0);

        //Ajout du ptn de départ
        listNoeudsPasVu.add(dep);
        meilleurCout.put(dep, 0);

        while (!listNoeudsPasVu.isEmpty()) {

            //Noeud avec coût minimal actuel
            Noeud noeudActuel = listNoeudsPasVu.poll();

            if (listNoeudsVu.contains(noeudActuel))
                continue;

            //On add le noeud actuel à la liste des noeuds passés
            listNoeudsVu.add(noeudActuel);

            //On check les voisins
            for (Noeud voisin : DeplacementMethodes.getVoisins(noeudActuel, map, lignes, colonnes)) {

                //Déjà traité
                if (listNoeudsVu.contains(voisin))
                    continue;

                //On calcule le poids du voisin en question
                int nvmCout = noeudActuel.getCoutDepuisDeprt() + DeplacementMethodes.getCout(map, voisin.getX(), voisin.getY());

                //Si il en existe un meilleur on skip
                if (meilleurCout.containsKey(voisin) && nvmCout >= meilleurCout.get(voisin))
                    continue;

                //Sinon on le met dedans (logique)
                meilleurCout.put(voisin, nvmCout);

                //On set le voisin avec ce nvm meilleur cout
                voisin.setCoutDepuisDeprt(nvmCout);
                //On ajoute comme parent du voisin le noeud actuel (permet de remonter à dep)
                voisin.setParent(noeudActuel);

                //On ajoute le voisin en question à la liste des non-vus
                listNoeudsPasVu.add(voisin);
            }
        }

        return meilleurCout;
    }

    public static void main(String[] args) {
        GameWorld w=new GameWorld();
        /*int[][] map = new int[][]{
                {0, 1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1, 1},
                {2, 2, 1, 2, 2, 2},
                {0, 1, 1, 1, 0, 0},
                {1, 1, 2, 2, 2, 2},
                {0, 1, 2, 2, 1, 1}
        };

         */

        Map<Noeud, Integer> distances = new DeplacementDijkstra(w).calculerDistances(0, 0);

        for (Map.Entry<Noeud, Integer> entry : distances.entrySet()) {
            Noeud n = entry.getKey();
            System.out.println("(" + n.getX() + "," + n.getY() + ") min : " + entry.getValue());
            System.out.println("(" + n.getX() + "," + n.getY() + ") min : " + entry.getValue());
            System.out.println("(" + n.getX() + "," + n.getY() + ") min : " + entry.getValue());
            System.out.println("(" + n.getX() + "," + n.getY() + ") min : " + entry.getValue());
        }
    }

    /*
    objet car besoin d'un différent pour le chien

    stock dikstra de toute la map + listener sur liste barrage pour le recalculer
    méthode déplacement(Entite entite):
        cherche noeud equivalent au coordonnées de entité
        donne à entite les coordonnées du noeud parent etc
    fonction correspondance noeud/coord entite?
    
     */

    public double[] getTile(double x, double y){
        return new double[]{Utilitaire.divisionEuclidienne(x, w.getTailleTile()), Utilitaire.divisionEuclidienne(y, w.getTailleTile())};
    }


}