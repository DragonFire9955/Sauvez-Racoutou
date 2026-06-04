package app.Modele.Chemins;

import app.Modele.GameWorld;
import app.Modele.Utilitaires.Noeud;

import java.util.*;

public class DeplacementDijkstra {

    private GameWorld w;
    private int[][] map;
    private int lignes;
    private int colonnes;
    private int tailleTile;
/*
    public DeplacementDijkstra(int[][] map) {

        this.map = map;
        this.lignes = map.length;
        this.colonnes = map[0].length;
    }

 */

    public DeplacementDijkstra(int tailleTile, int[][] map) {
        this.map=map;
        this.lignes = map.length;
        this.colonnes = map[0].length;
        this.tailleTile=tailleTile;
    }

    //Ajout du ptn de départ
    public Map<Double, Noeud> calculerDistances(int[] coord) {

        Noeud dep = new Noeud(coord[0], coord[1]);

        //On crée une liste qui trie pour le meilleur getCoutEstimDepArr (plus petit -> plus grand)
        PriorityQueue<Noeud> listNoeudsPasVu = new PriorityQueue<>(Comparator.comparingInt(Noeud::getCoutDepuisDeprt));

        //Recevra touts les noeuds passés
        Set<Noeud> listNoeudsVu = new HashSet<>();

        //Pr chq noeud, le meilleur coût
        Map<Double, Noeud> mapNoeuds = new HashMap<>();

        //Init
        dep.setCoutDepuisDeprt(0);

        //Ajout du pnt de départ
        listNoeudsPasVu.add(dep);
        mapNoeuds.put(coordToDouble(coord), dep);
        //System.out.println("voisin racoutou "+ dep.getParent());

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
                int nvmCout = noeudActuel.getCoutDepuisDeprt() + DeplacementMethodes.getCout(map, voisin.getI(), voisin.getJ());

                //Si il en existe un meilleur on skip
                if (mapNoeuds.containsValue(voisin)
                        && nvmCout >= voisin.getCoutDepuisDeprt())
                    continue;
                //On set le voisin avec ce nvm meilleur cout
                voisin.setCoutDepuisDeprt(nvmCout);

                //On ajoute comme parent du voisin le noeud actuel (permet de remonter à dep)
                voisin.setParent(noeudActuel);

                //Sinon on le met dedans (logique)
                mapNoeuds.put(coordToDouble(voisin.getCoord()), voisin);

                //On ajoute le voisin en question à la liste des non-vus
                listNoeudsPasVu.add(voisin);

            }
        }

        return mapNoeuds;
    }

    public HashMap<Noeud, Noeud> testDijkstra(double[] dep){

        //Noeud actuel = point de départ
        Noeud actuel= new Noeud(getTile(tailleTile, dep)[0], getTile(tailleTile, dep)[1]);

        actuel.setParent(null);
        actuel.setCoutDepuisDeprt(0);

        PriorityQueue<Noeud> fifo = new PriorityQueue<>(Comparator.comparingInt(Noeud::getCoutDepuisDeprt));
        HashMap<Noeud, Integer> noeudsVisites = new HashMap<>();
        HashMap<Noeud, Noeud> parent = new HashMap<>();
        fifo.add(actuel);
        parent.put(actuel, null);


        while(actuel!=null){
            //si actuel pas encore visité
            if(!noeudsVisites.containsKey(actuel)){
                //ajout actuel à noeuds Visités
                noeudsVisites.put(actuel, actuel.getCoutDepuisDeprt());

                //Pour tous les voisins du noeud actuel

                for(Noeud voisin: DeplacementMethodes.getVoisins(actuel, map, lignes, colonnes)) {

                    //Si le voisin n'a pas été visité
                    if (!noeudsVisites.containsKey(voisin)){

                        int nouveauCout =actuel.getCoutDepuisDeprt()+ DeplacementMethodes.getCout(map, voisin.getI(), voisin.getJ());
                        //Si dans la fifo + nvCout< ancienCout: l'enlève de la fifo
                        if(fifo.contains(voisin) && nouveauCout<voisin.getCoutDepuisDeprt())
                            fifo.remove(voisin);

                        //Si pas dans la fifo
                        if(!fifo.contains(voisin)) {
                            //set le cout
                            voisin.setCoutDepuisDeprt(actuel.getCoutDepuisDeprt() + DeplacementMethodes.getCout(map, voisin.getI(), voisin.getJ()));
                            //set le parent: necessaire?
                            voisin.setParent(actuel);
                            //ajout à la fifo
                            fifo.add(voisin);
                            //ajout à la map parent <Noeud, Prédecesseur>
                            parent.put(voisin, actuel);
                        }
                    }
                }
            }
            actuel=fifo.poll();
        }
        return parent;
    }

    public static void main(String[] args) {
        int tailleTile = 32;

        int[][] map = new int[][]{
                /*{0, 1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1, 1},
                {2, 2, 1, 2, 2, 2},
                {0, 1, 1, 1, 0, 0},
                {1, 1, 2, 2, 2, 2},
                {0, 1, 2, 2, 1, 1}

                 */

                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };

        int i, j;
        Map<Noeud, Noeud> parents= new DeplacementDijkstra(tailleTile, map).testDijkstra(new double[]{0,0});
        for (int ligne = 0; ligne<  map.length; ligne++) {
            for (int col = 0; col < map[ligne].length; col++) {

                Noeud n = new Noeud(ligne, col);
                if(parents.get(n) !=null) {
                    i = parents.get(n).getI();
                    j = parents.get(n).getJ();
                }
                else{
                    i=-1;
                    j=-1;
                }
            }
        }
        /*
        Map<Double, Noeud> distances = new DeplacementDijkstra(w.getTailleTile(), map).calculerDistances(new int[]{0, 0});

        for (int i = 0; i<  map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.println(i +", "+j+": "+distances.get(coordToDouble(new int[]{j, i})));
            }
        }

         */
/*
        for (Map.Entry<Double, Noeud> entry : distances.entrySet()) {
            Noeud n = entry.getValue();
        }

 */
    }
/*
    public static Noeud trouverNoeud(Map<Noeud, Integer> liste, int[] coord){



        for (Map.Entry<Noeud, Integer> entry : liste.entrySet()) {
            Noeud n = entry.getKey();
            System.out.println("nX: "+ n.getX()+ " nY: "+ n.getY() +" coordX: "+ coord[0] + "coordY: "+ coord[1]);
            if()
                return n;
        }
        return null;
    }

 */

    /*
    objet car besoin d'un différent pour le chien

    stock dikstra de toute la map + listener sur liste barrage pour le recalculer
    méthode déplacement(Entite entite):
        cherche noeud equivalent au coordonnées de entité
        donne à entite les coordonnées du noeud parent etc
    fonction correspondance noeud/coord entite?
    
     */

    public static double coordToDouble(int[] coord) {
        return coord[0] + ((double) coord[1] / 100);
        //i,j
    }

    public int[] getTile(int tailleTile, double[] coord){
        return new int[]{(int) (coord[1]/tailleTile), (int) (coord[0]/tailleTile)};
    }

    private int manhattan(int ia, int ja, int ib, int jb) {
        return Math.abs(ia - ib) + Math.abs(ja- jb);
    }



}