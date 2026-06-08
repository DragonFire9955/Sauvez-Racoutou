package app.Vue;

import app.Controller.ControleurDeClic;
import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private Image base = new Image(getClass().getResourceAsStream("/app/images/herbe.png"), 32, 32, true, true);
    private Image sol = new Image(getClass().getResourceAsStream("/app/images/sol.png"), 32, 32, true, true);
    private Image tonneau = new Image(getClass().getResourceAsStream("/app/images/tonneau4.png"), 32, 32, true, true);
    private Image hor = new Image(getClass().getResourceAsStream("/app/images/horizontal.png"), 32, 32, false, true);
    private Image ver = new Image(getClass().getResourceAsStream("/app/images/vertical2.png"), 32, 32, false, true);

    private Image poubelle = new Image(getClass().getResourceAsStream("/app/images/poubelle.png"), 32, 32, false, true);
    private Image classique = new Image(getClass().getResourceAsStream("/app/images/chat.png"), 32, 32, false, true);
    private Image sniper = new Image(getClass().getResourceAsStream("/app/images/sniper.png"), 32, 32, false, true);
    private Image journaliste = new Image(getClass().getResourceAsStream("/app/images/journaliste.png"), 32, 32, false, true);

    private int ligne;

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
                cases.setImage(base);
                break;
            case 2:
                cases.setImage(tonneau);
                break;
            case 3:
                cases.setImage(hor);
                break;
            case 4:
                cases.setImage(ver);
                break;
            default:
                cases.setImage(sol);
                break;
        }

        return cases;
    }

    public ImageView creerTour(int idEntite) {
        ImageView tour = new ImageView();

        switch (idEntite) {
            case 100:
                tour.setImage(poubelle);
                break;
            default:
                System.out.println("Barrage inconnu");
                break;
        }

        return tour;
    }

    public void remplirMap(TilePane tileMap, int[][] map, ControleurDeClic clicControl) {
        tileMap.getChildren().clear(); //on vide le images pour ne pas superposer

        for (int l = 0; l < map.length; l++) {
            for (int c = 0; c < map[l].length; c++) {

                ImageView tuile = creerTuile(map[l][c]); //on rempli avec les images

                tuile.addEventHandler(MouseEvent.MOUSE_CLICKED, clicControl);

                tileMap.getChildren().add(tuile);
            }
        }
    }

}