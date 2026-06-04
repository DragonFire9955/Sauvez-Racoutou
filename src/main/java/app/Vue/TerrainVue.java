package app.Vue;

import app.Modele.Entites.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private Image base = new Image(getClass().getResourceAsStream("/app/images/herbe.png"), 32, 32, true, true);
    private Image sol = new Image(getClass().getResourceAsStream("/app/images/sol.png"), 32, 32, true, true);
    private Image tonneau = new Image(getClass().getResourceAsStream("/app/images/herbe.png"), 32, 32, true, true);
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

    public ImageView creerTuile(int tuile){
        ImageView cases = new ImageView();

        switch (tuile) {
            case 0:
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

    public ImageView creerTour(Entite entite) {
        ImageView vue = new ImageView();

        switch (entite.getIdEntite()) {
            case 100:
                vue.setImage(poubelle);
                break;
            default:
                System.out.println("Barrage inconnu");
                break;
        }

        return vue;
    }
}