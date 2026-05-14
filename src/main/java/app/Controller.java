package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane gamePane;
    @FXML private Rectangle ennemi1;
    @FXML private Rectangle player;
    @FXML private Pane carte;

    @FXML
    private TilePane tileMap;
    private int[][] map;
    private Timeline gameLoop;
    private int temps;

    double vitesse;
    private Image vert = new Image(getClass().getResourceAsStream("/app/images/vert.png"), 32, 32, true, true);
    private Image marron = new Image(getClass().getResourceAsStream("/app/images/marron.png"), 32, 32, true, true);
    private Image beige = new Image(getClass().getResourceAsStream("/app/images/beige.png"), 32, 32, true, true);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vitesse = 2;

        initAnimation();
        // demarre l'animation
        gameLoop.play();
        this.map = Terrain.genererMap();
        Terrain.delimitationMap(tileMap);

        Terrain.couleurMap(tileMap, map, vert, marron, beige);

        keyEventManager();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    /*if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }*/
                    /*else*/if (temps%5==0){
                        vaVers();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void keyEventManager() {

        gamePane.sceneProperty().addListener((observable, oldScene, newScene) -> {

            if (newScene != null) {
                gamePane.requestFocus();

                newScene.setOnKeyPressed(event -> {
                    gestionCamera(event);
                    remisePositionTest(event);
                });

                newScene.setOnScroll(event -> {
                    double zoom = event.getDeltaY() > 0 ? 1.1 : 0.9;

                    carte.setScaleX(carte.getScaleX() * zoom);
                    carte.setScaleY(carte.getScaleY() * zoom);
                });
            }
        });
    }

    private void gestionCamera(KeyEvent event) {

        switch (event.getCode()) {
            case LEFT:
                carte.setTranslateX(carte.getTranslateX() + 20);
                break;
            case RIGHT:
                carte.setTranslateX(carte.getTranslateX() - 20);
                break;
            case UP:
                carte.setTranslateY(carte.getTranslateY() + 20);
                break;
            case DOWN:
                carte.setTranslateY(carte.getTranslateY() - 20);
                break;
        }
    }

    private void vaVers() {

        double dx = player.getLayoutX() - ennemi1.getLayoutX();
        double dy = player.getLayoutY() - ennemi1.getLayoutY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {return;}

        dx /= dist;
        dy /= dist;

        ennemi1.setLayoutX(ennemi1.getLayoutX() + dx * vitesse);
        ennemi1.setLayoutY(ennemi1.getLayoutY() + dy * vitesse);
    }

    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remisePositionTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("position rétablie");

            ennemi1.setLayoutX(400);
            ennemi1.setLayoutY(400);
        }
    }
}