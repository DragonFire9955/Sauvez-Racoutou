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
                    deplacementClavierCamera(event);
                    remisePositionTest(event);
                });

                //Zoom Camera
                newScene.setOnScroll(event -> {
                    double zoom = event.getDeltaY() > 0 ? 1.1 : 0.9;

                    if (carte.getScaleX() * zoom < 0.5 || carte.getScaleX() * zoom > 2.5) return;
                    carte.setScaleX(carte.getScaleX() * zoom);
                    carte.setScaleY(carte.getScaleY() * zoom);
                });
            }
        });
    }

    private void deplacementClavierCamera(KeyEvent event) {

        switch (event.getCode()) {

            /*Explications : je fait bouger la carte du minimum / maximum à chaque actions :
            Si c'est pour aller à gauche ou en haut, on va prendre la valeur minimum entre 0, le bord de base, et
            la valeur résultante de notre présuposé déplacement.
            ex : Si carte.getTranslateX = -30, si on lui ajoute 20 ça va faire -10 qui est plus petit que 0 donc on translate de -10,
                Si carte.getTranslateX = -10, si on lui ajoute 20 ça va faire 10 qui est plus grand que 0 donc on translate de 0, donc on bouge pas.
            Si c'est pour aller en bas ou à droite, là il va falloir check si c'est pas un trop grand nombre.
            le long morceau dans la 2ème partie de Math.max est la hauteur de notre écran (gamePane) auquel on enlève
            la largeur de la map.
            ex : Si hauteur du gamePane = 600 et que la hauteur de la map est de 1000, la limite est de -400
                Donc 0 -> haut de la map et -400 -> bas de la map.
                Si on est en -350 et que on veut descendre de 20, ça va faire -370 qui est plus grand que -400 donc autorisé (oui c le plus proche de 0 attention au (-) XD)
                Si on est en -390 et que on veut descendre de 20, ça va faire -410 qui est plus petit que -400 donc on bouge à la limite.
             */
            case LEFT:
                carte.setTranslateX(Math.min(carte.getTranslateX() + 20, 0));
                break;
            case RIGHT:
                carte.setTranslateX(Math.max(carte.getTranslateX() - 20, gamePane.getWidth() - tileMap.getPrefTileWidth() * tileMap.getPrefColumns()));
                break;
            case UP:
                carte.setTranslateY(Math.min(carte.getTranslateY() + 20, 0));
                break;
            case DOWN:
                carte.setTranslateY(Math.max(carte.getTranslateY() - 20, gamePane.getHeight() - tileMap.getPrefTileHeight() * tileMap.getPrefRows()));
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