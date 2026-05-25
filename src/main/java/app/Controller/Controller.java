package app.Controller;

import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Animaux.Ennemis.PouletClassique;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Terrain;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Menu fxml loader
    public Pane menuFXMLLoader = MenuController.stock;

    // VUE
    @FXML private BorderPane applicationPane;

    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private Pane demarragePane;

    @FXML private TilePane tileMap;

    private TerrainVue terrainVue;

    //MODELE
    private int[][] map;
    private GameWorld gameWorld;

    //CONTROLEUR
    private CameraManager cameraManager;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        terrainVue = new TerrainVue();

        this.map = Terrain.genererMap();
        terrainVue.delimitationMap(tileMap);

        terrainVue.couleurMap(tileMap, map);

        //Initialisation des Managers
        gameWorld = new GameWorld();
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> gameLoop.stop());
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();


        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new EntitesListListener(carte));
        gameWorld.ajouterAllie(new Racoutou(gameWorld));
        gameWorld.ajouterEnnemi(new PouletClassique(EnnemisSpawn.randomCoord(gameWorld), gameWorld));


        initAnimation();
        // demarre l'animation
        gameLoop.play();
        gameWorld.getTheEnd().addListener((observable, oldValue, newValue) ->{
            gameLoop.stop();

            /*try {
                menuFXMLLoader = FXMLLoader.load(getClass().getResource("/app/starting.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

             */

            applicationPane.getScene().setRoot(menuFXMLLoader);
        });

        gamePane.sceneProperty().addListener((observable, oldValue, newValue) -> {

            gamePane.setFocusTraversable(true);
            gamePane.requestFocus();

            //On met tout les évènements claviers
            gamePane.setOnKeyPressed(event -> {

                remetEnnemiTest(event);
            });
        });
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017),(ev ->{
            gameWorld.updateGW(temps);
            temps++;
        }));
        gameLoop.getKeyFrames().add(kf);
    }

    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remetEnnemiTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("nouveau PouletClassique");

            gameWorld.ajouterEnnemi(new PouletClassique(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        }
    }

    //Partie render des Animaux sur la scène
   /* private void affichageAnimaux() {

        for (Animaux a: gameWorld.getAnimaux()) {

        }
    }*/

    /*private void cleanupViews() {

        //J'ai mis un Iterator car il permet de delet sans avoir de prb d'index
        Iterator<Animaux> iterator = gameWorld.getAnimaux().iterator();

        while (iterator.hasNext()) {

            Animaux a = iterator.next();    //Je le stocke car si on le save pas on peux pas l'use, car on passera au suivant si on remet iterator.next

            if (!a.isAlive()) {

                iterator.remove();
                carte.getChildren().remove(animauxVue.remove(a));
            }
        }
    }

     */


}