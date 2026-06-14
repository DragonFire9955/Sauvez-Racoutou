package app.Vue;

import app.Controller.Listener.ControleurDeClic;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    public void delimitationMap(TilePane tileMap){
        tileMap.setPrefColumns(44);
        tileMap.setPrefRows(28);
        tileMap.setPrefTileWidth(64);
        tileMap.setPrefTileHeight(64);
    }

    public ImageView creerTuile(int tuile){
        ImageView cases = new ImageView();

        switch (tuile) {
            case 1:
                cases.setImage(ImageSetter.sol);
                break;
            case 5:
                cases.setImage(ImageSetter.tonneau);
                break;
        //PLACE
            case 11:
                cases.setImage(ImageSetter.placeCoinHG);
                break;
            case 12:
                cases.setImage(ImageSetter.placeCoinHD);
                break;
            case 13:
                cases.setImage(ImageSetter.placeCoinBG);
                break;
            case 14:
                cases.setImage(ImageSetter.placeCoinBD);
                break;
            case 15:
                cases.setImage(ImageSetter.placeCoteH);
                break;
            case 16:
                cases.setImage(ImageSetter.placeCoteB);
                break;
            case 17:
                cases.setImage(ImageSetter.placeCoteG);
                break;
            case 18:
                cases.setImage(ImageSetter.placeCoteD);
                break;
            case 19:
                cases.setImage(ImageSetter.base);
                break;

        // BATIMENTS
            case 20:
                cases.setImage(ImageSetter.batCoinG);
                break;
            case 21:
                cases.setImage(ImageSetter.batCoinD);
                break;
            case 22:
                cases.setImage(ImageSetter.batCoteG);
                break;
            case 23:
                cases.setImage(ImageSetter.batCoteD);
                break;
            case 24:
                cases.setImage(ImageSetter.batCoteB);
                break;
            case 25:
                cases.setImage(ImageSetter.batCentre);
                break;

            default:
                cases.setImage(ImageSetter.sol);
                break;
        }
        cases.setFitHeight(64);
        cases.setFitWidth(64);
        cases.setPreserveRatio(true);
        cases.setSmooth(true);
        cases.setCache(true);
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