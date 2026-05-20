package app.Controller;

import app.Modele.Entites.Animaux;
import app.Modele.Entites.AnimauxFolder.Allies.Racoutou;
import app.Modele.Entites.AnimauxFolder.Ennemis.PouletClassique;
//import app.Modele.Entites.AnimauxFolder.Ennemis.PouletConservateur;
import app.Modele.GameWorld;
import app.Terrain;
import app.TerrainVue;
import app.Vue.AnimalVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import static app.Vue.image.*;
import static javafx.geometry.Pos.CENTER;

public class Controller implements Initializable {

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
    private boolean jeuLance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jeuLance = false;
        carte.setVisible(false);
        carte.setDisable(true);

        terrainVue = new TerrainVue();

        this.map = Terrain.genererMap();
        terrainVue.delimitationMap(tileMap);

        terrainVue.couleurMap(tileMap, map);

        //Initialisation des Managers
        gameWorld = new GameWorld();
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();


        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new AnimauxListListener(carte));
        gameWorld.ajouterAllie(new Racoutou(gameWorld));
        gameWorld.ajouterEnnemi(new PouletClassique(gameWorld));


        initAnimation();
        // demarre l'animation
        gameLoop.play();

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
                    /*else*/if (temps%5==0 && jeuLance){

                        gameWorld.updateGW(temps);

                        gameWorld.supprimerAnimauxMorts();
                        //cleanupViews();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML //Bouton "Lancer Jeu"
    public void lancerJeu() {

        demarragePane.setVisible(false);
        demarragePane.setDisable(true);
        carte.setVisible(true);

        Label titreLabel = new Label("Sauvez Racoutou");
        titreLabel.setPrefWidth(applicationPane.getPrefWidth());
        titreLabel.setStyle("-fx-font: italic bold 65px 'Rockwell';");
        titreLabel.setAlignment(CENTER);

        applicationPane.setTop(titreLabel);

        jeuLance = true;
    }



    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remetEnnemiTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("nouveau PouletClassique");

            gameWorld.ajouterEnnemi(new PouletClassique(gameWorld));
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