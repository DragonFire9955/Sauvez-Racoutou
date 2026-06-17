package app.Modele.Managers;

import app.Modele.GameWorld;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

public class EnnemisSpawn {

    public static List<int[]> randomCoordDef(GameWorld w){
        int nbLignes = w.getMap().length;
        int nbCol = w.getMap()[0].length;
        List<int[]> tilesPossibles = new ArrayList<>();

        //Bords horizontaux
        for(int i=0; i<nbCol; i++){
            if(w.getMap()[0][i] == 1)
                tilesPossibles.add( new int[]{0, i});
            if(w.getMap()[nbLignes-1][i] == 1)
                tilesPossibles.add( new int[]{(nbLignes-1), i});
        }

        //Bords verticaux (sans compter en double les tuiles coins
        for(int j=1; j<nbLignes-1; j++){
            if(w.getMap()[j][0] == 1)
                tilesPossibles.add( new int[]{j, 0});
            if(w.getMap()[j][nbCol-1] == 1)
                tilesPossibles.add( new int[]{j, nbCol-1});
        }
        return tilesPossibles;
    }

    public static double[] randomCoord (GameWorld w){
        List<int[]> tilesPossibles = randomCoordDef(w);
        int[] tuile = tilesPossibles.get((int) (Math.random()*tilesPossibles.size()));

        return new double[] { (tuile[1]+ Math.random())*w.getTailleTile(), (tuile[0] +  Math.random())*w.getTailleTile()};
    }




}
