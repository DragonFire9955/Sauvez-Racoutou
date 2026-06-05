package app.Modele.Chemins;

import app.Modele.Utilitaires.Noeud;

import java.util.*;

public class DeplacementA {

    private int[][] map;
    private int lignes;
    private int colonnes;

    public DeplacementA(int[][] map) {

        this.map = map;
        this.lignes = map.length;
        this.colonnes = map[0].length;
    }

    //Calcul de la distance (check distance de Manhattan cour de maths)
    private int heuristique(Noeud a, Noeud b) {
        return Math.abs(a.getI() - b.getI()) + Math.abs(a.getJ()- b.getJ());
    }

    //On envoie pos départ et arrivée
    public List<Noeud> trouverChemin(int startX, int startY, int endX, int endY) {

        Noeud dep = new Noeud(startX, startY);
        Noeud arr = new Noeud(endX, endY);

        //On crée une liste qui trie pour le meilleur getCoutEstimDepArr (plus petit -> plus grand)
        /// TODO : nom à changer ? XD
        PriorityQueue<Noeud> listNoeudsPasVu = new PriorityQueue<>(Comparator.comparingInt(Noeud::getCoutDepuisDeprt));

        //Recevra touts les noeuds passés
        Set<Noeud> listNoeudsVu = new HashSet<>();

        //Pr chq noeud, le meilleur coût
        Map<Noeud, Integer> meilleurCout = new HashMap<>();

        //Init
        dep.setCoutDepuisDeprt(0);
        dep.setCoutEstimArr(heuristique(dep, arr));
        dep.setCoutEstimDepArr(dep.getCoutDepuisDeprt() + dep.getCoutEstimArr());

        //Ajout du ptn de départ
        listNoeudsPasVu.add(dep);
        meilleurCout.put(dep, 0);

        //Début de la boucle "tant que c pas fini"
        while (!listNoeudsPasVu.isEmpty()) {

            //On récup la case avec le plus petit getCoutEstimDepArr
            Noeud noeudActuel = listNoeudsPasVu.poll();

            if (listNoeudsVu.contains(noeudActuel))
                continue;

            //Si on atteint le max on repart du début pr recalculer un chemin potentioellement mieux
            if (noeudActuel.getI() == arr.getI() && noeudActuel.getJ() == arr.getJ())
                return DeplacementMethodes.reconstruireChemin(noeudActuel);

            //On add le noeud actuel à la liste des noeuds passés
            listNoeudsVu.add(noeudActuel);

            //On recherche "nos chers voisins" (ceux qui ont la ref :P)
            for (Noeud voisin : DeplacementMethodes.getVoisins(noeudActuel, map, lignes, colonnes)) {

                //Si le noeud est déjà check on passe au suivant (oui bon ils détestent continue mais je le pense bien ici)
                if (listNoeudsVu.contains(voisin))
                    continue;

                //On calcule le poids du voisin en question
                int nvmCout = noeudActuel.getCoutDepuisDeprt() + DeplacementMethodes.getCout(map, voisin.getI(), voisin.getJ());

                //Si il en existe un meilleur on skip
                if (meilleurCout.containsKey(voisin) && nvmCout >= meilleurCout.get(voisin))
                    continue;

                //Sinon on le met dedans (logique)
                meilleurCout.put(voisin, nvmCout);

                //On set le voisin avec ce nvm meilleur cout
                voisin.setCoutDepuisDeprt(nvmCout);
                //On estime la distance jusqu'à l'arrivée
                voisin.setCoutEstimArr(heuristique(voisin, arr));
                //On calcule l'addition des 2 (comme ça on sait lequel des voisins a les moins couteux une fois la boucle finie)
                voisin.setCoutEstimDepArr(voisin.getCoutDepuisDeprt() + voisin.getCoutEstimArr());

                //On ajoute comme parent du voisin le noeud actuel (permet de remonter à dep)
                voisin.setParent(noeudActuel);

                //On ajoute le voisin en question à la liste des non-vus
                listNoeudsPasVu.add(voisin);
            }
        }

        return new ArrayList<>();
    }


    public static void main(String[] args) {
        int[][] map = new int[][]{
                {0, 1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1, 1},
                {2, 2, 1, 2, 2, 2},
                {0, 1, 1, 1, 0, 0},
                {1, 1, 2, 2, 2, 2},
                {0, 1, 2, 2, 1, 1}
        };

        DeplacementA dp = new DeplacementA(map);
        List<Noeud> chemin = dp.trouverChemin(0, 0, 0, 5);
        for (Noeud coord : chemin) {
            System.out.println("(" + coord.getI() + "," + coord.getJ() + ")");
        }
    }
}