package app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Terrain {

    private static final int longueur = 44;
    private static final int hauteur = 28;

    public static int[][] genererMap() {
        int[][] map = new int[longueur][hauteur];
        //Tuile de référence pour se repérer sur la carte
        map[0][0] = 1;
        map[longueur-1][hauteur-1] = 1;
        return map;
    }

    public static void delimitationMap(TilePane tileMap){
        tileMap.setPrefColumns(longueur);
        tileMap.setPrefRows(hauteur);
        tileMap.setPrefTileWidth(32);
        tileMap.setPrefTileHeight(32);
    }

    public static void couleurMap(TilePane tileMap, int[][] map, Image c1, Image c2, Image c3){

        for (int l = 0; l < map.length; l++) {
            //System.out.println("line " + l + ", first tile : " + map[l][0]);  Check de la position de chaque tiles de début
            for (int c = 0; c < map[l].length; c++) {

                ImageView cases = new ImageView();

                if (map[l][c] == 0) {
                    cases.setImage(c1);
                } else if (map[l][c] == 1){
                    cases.setImage(c2);
                } else {
                    cases.setImage(c3);
                }

                tileMap.getChildren().add(cases);
            }
        }
    }
}