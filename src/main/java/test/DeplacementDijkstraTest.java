package test;

import app.Modele.Chemins.DeplacementDijkstra;
import app.Modele.Utilitaires.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DeplacementDijkstraTest {

    private int[][] mapSimple;
    private int tailleTile;
    private DeplacementDijkstra dijkstraAlgo;

    @BeforeEach
    public void debut() {
        tailleTile = 64;
        // Une map 3x3 simple rempli de 0
        mapSimple = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        dijkstraAlgo = new DeplacementDijkstra(tailleTile, mapSimple);
    }

    @Test
    public void testGetTile() {
        //si un joueur clique sur la case x=70 et y=130
        //avc les cases a 64px ca fais la colonne 1 et la ligne 2
        double[] coordPixels = new double[]{70.0, 130.0};

        int[] tile = dijkstraAlgo.getTile(tailleTile, coordPixels);

        assertEquals(2, tile[0], "La ligne doit être 2");
        assertEquals(1, tile[1], "La colonne doit être 1");
    }

    @Test
    public void testDijkstraCible() {
        //départ a 0,0
        double[] depart = new double[]{0.0, 0.0};
        //arrive  à 0,2 (tuile a 64px)
        double[] arrive = new double[]{128.0, 0.0};

        HashMap<Noeud, Noeud> parents = dijkstraAlgo.dijkstraCible(depart, arrive);

        //parcours emprunté
        Noeud noeudCible = new Noeud(0, 2);
        Noeud noeudMilieu = new Noeud(0, 1);
        Noeud noeudDepart = new Noeud(0, 0);

        assertEquals(noeudMilieu, parents.get(noeudCible), "Le parent de (0,2) doit être (0,1)");

        assertEquals(noeudDepart, parents.get(noeudMilieu), "Le parent de (0,1) doit être (0,0)");

        assertNull(parents.get(noeudDepart), "Le départ n'a pas de parent");
    }
}