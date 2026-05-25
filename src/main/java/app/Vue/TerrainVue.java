package app.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private Image base = new Image(getClass().getResourceAsStream("/app/images/herbe.png"), 32, 32, true, true);
    private Image sol = new Image(getClass().getResourceAsStream("/app/images/sol.png"), 32, 32, true, true);
    private Image tonneau = new Image(getClass().getResourceAsStream("/app/images/tonneau4.png"), 32, 32, true, true);
    private Image hor = new Image(getClass().getResourceAsStream("/app/images/horizontal.png"), 32, 32, false, true);
    private Image ver = new Image(getClass().getResourceAsStream("/app/images/vertical2.png"), 32, 32, false, true);

    private Image poubelle = new Image(getClass().getResourceAsStream("/app/images/poubelle.png"), 32, 32, false, true);
    private Image classique = new Image(getClass().getResourceAsStream("/app/images/classique.png"), 32, 32, false, true);
    private Image projectiles = new Image(getClass().getResourceAsStream("/app/images/projectiles.png"), 32, 32, false, true);
    private Image journaliste = new Image(getClass().getResourceAsStream("/app/images/journaliste.png"), 32, 32, false, true);

    public void delimitationMap(TilePane tileMap){
        tileMap.setPrefColumns(44);
        tileMap.setPrefRows(28);
        tileMap.setPrefTileWidth(32);
        tileMap.setPrefTileHeight(32);
    }

    public ImageView creerCase(int tuile){

        ImageView cases = new ImageView();

        if (tuile == 0) {
            cases.setImage(sol);
        } else if (tuile == 1){
            cases.setImage(base);
        } else if (tuile == 2){
            cases.setImage(tonneau);
        } else if (tuile == 3){
            cases.setImage(hor);
        } else if (tuile == 4) {
            cases.setImage(ver);
        } else if (tuile == 100) {
            cases.setImage(poubelle);
        } else if (tuile == 101) {
            cases.setImage(classique);
        } else if (tuile == 102) {
            cases.setImage(projectiles);
        } else if (tuile == 103) {
            cases.setImage(journaliste);
        }

        return cases;


    }
}
