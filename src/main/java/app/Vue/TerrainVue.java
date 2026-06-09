package app.Vue;

import app.Controller.Listener.ControleurDeClic;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    public void delimitationMap(TilePane tileMap){
        tileMap.setPrefColumns(44);
        tileMap.setPrefRows(28);
        tileMap.setPrefTileWidth(32);
        tileMap.setPrefTileHeight(32);
    }

    public ImageView creerTuile(int tuile){
        ImageView cases = new ImageView();

        switch (tuile) {
            case 1:
                cases.setImage(ImageSetter.base);
                break;
            case 2:
                cases.setImage(ImageSetter.tonneau);
                break;
            case 3:
                cases.setImage(ImageSetter.hor);
                break;
            case 4:
                cases.setImage(ImageSetter.ver);
                break;
            default:
                cases.setImage(ImageSetter.sol);
                break;
        }

        return cases;
    }

    public ImageView creerTour(int idEntite) {
        ImageView tour = new ImageView();

        switch (idEntite) {
            case 100:
                tour.setImage(ImageSetter.poubelle);
                break;
            default:
                System.out.println("Barrage inconnu");
                break;
        }

        return tour;
    }

    public void remplirMap(TilePane tileMap, int[][] map) {
        tileMap.getChildren().clear(); //on vide le images pour ne pas superposer

        for (int l = 0; l < map.length; l++) {
            for (int c = 0; c < map[l].length; c++) {

                ImageView tuile = creerTuile(map[l][c]); //on rempli avec les images
                tileMap.getChildren().add(tuile);
            }
        }
    }

}