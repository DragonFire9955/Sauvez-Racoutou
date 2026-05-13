package universite_paris8iut.bcottinet.app;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;

public class HelloController {

    @FXML
    private TilePane tileMap;
    private int[][] map;

    private Image vert = new Image("file:/home/etudiants/info/bcottinet/IdeaProjects/test/src/main/resources/universite_paris8iut/bcottinet/app/vert.png", 32, 32, true, true);
    private Image marron = new Image("file:/home/etudiants/info/bcottinet/IdeaProjects/test/src/main/resources/universite_paris8iut/bcottinet/app/marron.png", 32, 32, true, true);
    private Image beige = new Image("file:/home/etudiants/info/bcottinet/IdeaProjects/test/src/main/resources/universite_paris8iut/bcottinet/app/beige.png", 32, 32, true, true);

    @FXML
    public void initialize() {
        this.map = Terrain.genererMap();
        Terrain.delimitationMap(tileMap);

        Terrain.couleurMap(tileMap, map, vert, marron, beige);
    }

}