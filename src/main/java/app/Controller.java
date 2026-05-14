/// TODO : RESIZE CARTE SI QUAND ON DEZOOM ON SORT DE LA CARTE

package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Scale scale;
    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private Rectangle ennemi1;
    @FXML private Rectangle player;

    @FXML private TilePane tileMap;
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

        //Initialisation d'un scale pour le bon fonctionnement du zoom
        scaleInit();

        //Fonction gérant touts les évènements clavier
        keyEventManager();
    }

    private void scaleInit() {

        scale = new Scale();

        scale.setPivotX(0);
        scale.setPivotY(0);

        scale.setX(1);
        scale.setY(1);

        carte.getTransforms().add(scale);
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
                    deplacementClavierCamera(event);
                    remisePositionTest(event);
                });

                //Zoom Camera
                newScene.setOnScroll(event -> {

                    double zoom = event.getDeltaY() > 0 ? 1.1 : 0.9;
                    double nouvZoom = scale.getX() * zoom;

                    if (nouvZoom < 1) {
                        nouvZoom = 1;
                    } else if (nouvZoom > 2.5) {
                        nouvZoom = 2.5;
                    }

                    scale.setX(nouvZoom);
                    scale.setY(nouvZoom);
                });
            }
        });
    }

    private void deplacementClavierCamera(KeyEvent event) {

        switch (event.getCode()) {

            case LEFT:
                carte.setTranslateX(Math.min(carte.getTranslateX() + (tileMap.getPrefTileWidth() * scale.getX()) / 2, 0));
                break;
            case RIGHT:
                carte.setTranslateX(Math.max(carte.getTranslateX() - (tileMap.getPrefTileWidth() * scale.getX()) / 2, gamePane.getWidth() - tileMap.getPrefTileWidth() * tileMap.getPrefColumns() * scale.getX()));
                break;
            case UP:
                carte.setTranslateY(Math.min(carte.getTranslateY() + (tileMap.getPrefTileHeight() * scale.getY()) / 2, 0));
                break;
            case DOWN:
                carte.setTranslateY(Math.max(carte.getTranslateY() - (tileMap.getPrefTileHeight() * scale.getY()) / 2, gamePane.getHeight() - tileMap.getPrefTileHeight() * tileMap.getPrefRows() * scale.getY()));
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