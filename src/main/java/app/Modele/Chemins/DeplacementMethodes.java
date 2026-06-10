package app.Modele.Chemins;

import app.Modele.Utilitaires.Noeud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeplacementMethodes {

    //On ajoute les coûts pour chaque id (donc chaque image)
    public static int getCout(int[][] map, int i, int j) {

        int type = map[i][j];

        switch (type) {
            //Les coûts sont à changer, c'est juste un exemple et ça correspond pas à la logique des images mais bon hein XD
            case 0:
                return 0;
            case 1:
                return 1;
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
        int[][] directionsSimples = {
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0}};
        int[][] diagonales = {
                //diagonales
                {1, 1},
                {-1, -1},
                {1, -1},
                {-1, 1}
        };

        for (int[] d : directionsSimples) {
            //n pour noeud
            int ni = noeud.getI() + d[0];
            int nj = noeud.getJ() + d[1];

            //Check des bordures

            if (ni>=0 && nj>=0 && ni<lignes && nj<colonnes) {
                //On skip les murs
                if (getCout(map, ni, nj) < 9999) {
                    voisins.add(new Noeud(ni, nj));
                }
            }
        }

        for(int[] d : diagonales) {
            //n pour noeud
            int ni = noeud.getI() + d[0];
            int nj = noeud.getJ() + d[1];

            //Check des bordures

            if (ni>=0 && nj>=0 && ni<lignes && nj<colonnes) {
                //On skip les murs
                if (getCout(map, ni, nj) < 9999
                 && getCout(map, ni, noeud.getJ()) < 9999
                 && getCout(map, noeud.getI(), nj) < 9999
                ) {
                    voisins.add(new Noeud(ni, nj));
                }
            }
        }

        if(noeud.equals(new Noeud(11, 21)))
            for(Noeud n: voisins){
            }

        return voisins;
    }

    public static int getCoutMinimal(Noeud noeud, List<Noeud> voisins, int[][] map, int lignes, int colonnes) {

        if (voisins.isEmpty()) {
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
