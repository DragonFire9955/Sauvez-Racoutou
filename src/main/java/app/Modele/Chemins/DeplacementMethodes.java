package app.Modele.Chemins;

import app.Modele.Utilitaires.Noeud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeplacementMethodes {

    //On ajoute les coûts pour chaque id (donc chaque image)
    public static int getCout(int[][] map, int i, int j) {

        int type = map[i][j];
        System.out.println("type: "+ type);

        switch (type) {
            //Les coûts sont à changer, c'est juste un exemple et ça correspond pas à la logique des images mais bon hein XD
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 9999;
            default:
                return 1;
        }
    }

    //On récup les voisins
    public static List<Noeud> getVoisins(Noeud noeud, int[][] map , int lignes, int colonnes) {

        List<Noeud> voisins = new ArrayList<>();

        ///TODO : Je pense on peut faire mieux car on sait que y a que 4 directions, à modif
        int[][] directions = {
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0},
                //diagonales
                {1, 1},
                {-1, -1},
                {1, -1},
                {-1, 1}
        };

        for (int[] d : directions) {

            System.out.println("d :" + d[0]+", "+d[1]);
            //n pour noeud
            int ni = noeud.getI() + d[0];
            int nj = noeud.getJ() + d[1];

            //Check des bordures

            if (ni>=0 && nj>=0 && ni<lignes && nj<colonnes) {
                //On skip les murs
                System.out.println("cout: " + (getCout(map, ni, nj)));
                if (getCout(map, ni, nj) < 9999) {
                    voisins.add(new Noeud(ni, nj));


                }
            }
        }

        if(noeud.equals(new Noeud(11, 21)))
            for(Noeud n: voisins){
                System.out.println("V  i: "+ n.getI() +" j: "+ n.getJ());
            }

        return voisins;
    }

    public static int getCoutMinimal(Noeud noeud, List<Noeud> voisins, int[][] map, int lignes, int colonnes) {

        if (voisins.isEmpty()) {
            System.out.println("DeplacementMethodes.getCoutMinimal : il n'y a plus de voisins");
            return 9999;
        }

        int noeudCoutMinimal = getCout(map, voisins.getFirst().getI(), voisins.getFirst().getJ());

        for (Noeud n : voisins) {

            if (getCout(map, n.getI(), n.getJ()) < noeudCoutMinimal)
                noeudCoutMinimal = getCout(map, n.getI(), n.getJ());
        }

        return noeudCoutMinimal;
    }

    //Un peu comme avec le C, on remonte jusqu'au head, bah là c la mm, chaque Noeud a un parent sauf le prems, le dep.
    public static List<Noeud> reconstruireChemin(Noeud fin) {

        List<Noeud> chemin = new ArrayList<>();

        Noeud courant = fin;

        while (courant != null) {

            chemin.add(courant);
            courant = courant.getParent();
        }

        Collections.reverse(chemin);

        return chemin;
    }
}
