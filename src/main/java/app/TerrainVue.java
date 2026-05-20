package app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private Image vert = new Image(getClass().getResourceAsStream("/app/images/vert.png"), 32, 32, true, true);
    private Image marron = new Image(getClass().getResourceAsStream("/app/images/marron.png"), 32, 32, true, true);
    private Image beige = new Image(getClass().getResourceAsStream("/app/images/beige.png"), 32, 32, true, true);

    public void delimitationMap(TilePane tileMap){
        tileMap.setPrefColumns(44);
        tileMap.setPrefRows(28);
        tileMap.setPrefTileWidth(32);
        tileMap.setPrefTileHeight(32);
    }

    public void couleurMap(TilePane tileMap, int[][] map){

        for (int l = 0; l < map.length; l++) {
            for (int c = 0; c < map[l].length; c++) {
                ImageView cases = new ImageView();

                if (map[l][c] == 0) {
                    cases.setImage(vert);
                } else if (map[l][c] == 1){
                    cases.setImage(marron);
                } else {
                    cases.setImage(beige);
                }

                tileMap.getChildren().add(cases);
            }
        }
    }

}
