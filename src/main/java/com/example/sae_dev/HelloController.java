package com.example.sae_dev;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class HelloController {

    @FXML
    private TilePane tileMap;
    private int[][] map;

    private Image vert = new Image("file:C:/Users/achil/IdeaProjects/SAE_Dev/src/vert.png", 32, 32, true, true);
    private Image marron = new Image("file:C:/Users/achil/IdeaProjects/SAE_Dev/src/marron.png", 32, 32, true, true);
    private Image beige = new Image("file:C:/Users/achil/IdeaProjects/SAE_Dev/src/beige.png", 32, 32, true, true);

    @FXML
    public void initialize() {
        this.map = Terrain.genererMap();
        Terrain.delimitationMap(tileMap);

        Terrain.couleurMap(tileMap, map, vert, marron, beige);
    }

}