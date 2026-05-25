package app.Controller;

import app.Modele.Entites.Animaux.Allies.Racoutou;
import app.Modele.Entites.Animaux.Ennemis.PouletBouclier;
import app.Modele.Entites.Animaux.Ennemis.PouletClassique;
import app.Modele.GameWorld;
import app.Modele.Terrain;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // VUE
    @FXML private BorderPane applicationPane;
    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private TilePane tileMap;

    private TerrainVue terrainVue;
    private boolean enPause = false;

    private boolean modePlacement = false;
    private int aPlacer = -1;

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

        remplirMap();

        //Initialisation des Managers
        gameWorld = new GameWorld();
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> gameLoop.stop());
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();


        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new EntitesListListener(carte));
        gameWorld.ajouterAllie(new Racoutou(gameWorld));
        gameWorld.ajouterEnnemi(new PouletClassique(200, 400, gameWorld));


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

    @FXML
    private void pause(){
        if (enPause){
            gameLoop.play();
        } else {
            gameLoop.pause();
        }

        enPause = !enPause;
        gamePane.requestFocus();
    }

    @FXML
    private void placerPoubelle(){
        modePlacement = true;
        aPlacer = 100;
    }

    @FXML
    private void placerChatClassique(){
        modePlacement = true;
        aPlacer = 101;
    }

    @FXML
    private void placerChatProjectiles(){
        modePlacement = true;
        aPlacer = 102;
    }

    @FXML
    private void placerChatJournaliste(){
        modePlacement = true;
        aPlacer = 103;
    }

    private void remplirMap(){
        tileMap.getChildren().clear();

        for(int l = 0; l < map.length; l++){
            int ligne = l;
            for(int c = 0; c < map[l].length; c++){
                int colonne = c;

                ImageView cases = terrainVue.creerCase(map[l][c]);

                cases.setOnMouseClicked(e -> {
                    if (modePlacement){
                        if (map[ligne][colonne] == 0){
                            map[ligne][colonne] = aPlacer;
                            modePlacement = false;

                            remplirMap();
                        } else {
                            System.out.println("Impossible de placer ici");
                        }
                    }
                });

                tileMap.getChildren().add(cases);

            }
        }

        gamePane.requestFocus();

    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017),(ev ->{
            if (temps%5==0){
                gameWorld.updateGW(temps);
            }
            temps++;
        }));
        gameLoop.getKeyFrames().add(kf);
    }

    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remetEnnemiTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("nouveau PouletClassique");

            gameWorld.ajouterEnnemi(new PouletClassique(200, 400, gameWorld));
        } else if (event.getCode() == KeyCode.A) {

            System.out.println("nouveau Racoutou");

            gameWorld.ajouterEnnemi(new Racoutou(gameWorld));
        } else if (event.getCode() == KeyCode.R) {

            System.out.println("nouveau PouletBouclier");

            gameWorld.ajouterEnnemi(new PouletBouclier(400, 200, gameWorld));
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