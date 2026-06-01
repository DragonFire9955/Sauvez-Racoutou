package app.Controller;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.ChatJournaliste;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.PouletMenotte;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.Entites.Barrage.Poubelle;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Terrain;
import app.Vue.CameraManager;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Window;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static app.Controller.MenuController.*;

public class Controller implements Initializable {

    // VUE
    @FXML private BorderPane applicationPane; // border pane parent de tous
    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private TilePane tileMap;
    @FXML private Button btnPoubelle;
    @FXML private Button btnClassique;
    @FXML private Button btnProjectiles;
    @FXML private Button btnJournaliste;


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

        this.map = Terrain.genererMap();
        terrainVue = new TerrainVue();
        terrainVue.delimitationMap(tileMap);

        remplirMap();

        DragAndDrop dragImage = new DragAndDrop();
        dragImage.drag(btnPoubelle, 100, "/app/images/poubelle.png");
        dragImage.drag(btnClassique, 101, "/app/images/classique.png");
        dragImage.drag(btnProjectiles, 102, "/app/images/projectiles.png");
        dragImage.drag(btnJournaliste, 103, "/app/images/journaliste.png");

        dragImage.survole(tileMap);

        // drop
        tileMap.setOnDragDropped(e -> { // réagit quand la souris relâche
            Dragboard db = e.getDragboard(); // on récupère le contenu
            int id = Integer.parseInt(db.getString()); // remet l'id en int

            int c = (int) (e.getX() / gameWorld.getTailleTile()); // colonne de la case
            int l = (int) (e.getY() / gameWorld.getTailleTile()); // ligne de la case

            if (map[l][c] == 0) { // si c'est du sol

                double posX = c * gameWorld.getTailleTile();
                double posY = l * gameWorld.getTailleTile();
                double[] coords = new double[]{posX, posY};

                if (id == 100) { //poubelle

                    Poubelle p = new Poubelle(coords, 100.0, 16.0, gameWorld);
                    ImageView imgPoubelle = terrainVue.creerTour(p);

                    imgPoubelle.setTranslateX(posX);
                    imgPoubelle.setTranslateY(posY);

                    carte.getChildren().add(imgPoubelle);

                    map[l][c] = p.getpoids();
                }

                remplirMap();
            } else {
                System.out.println("Impossible de placer ici");
            }

            e.consume();
            gamePane.requestFocus();
        });

        //Initialisation des Managers
        gameWorld = new GameWorld();
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> gameLoop.stop());
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();


        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new EntitesListListener(carte, gameWorld));
        gameWorld.ajouterAnimal(new Racoutou(gameWorld));
        gameWorld.getAnimaux().getFirst().getAliveProperty().addListener((observable, oldValue, newValue) -> {
                    gamePane.getScene().setRoot(menu);
                    isGameStarted.setValue(false);
                }
        );
        gameWorld.ajouterAnimal(AnimauxManager.creerPouletClassique(gameWorld));
        System.out.println(isGameStarted);
        isGameStarted.addListener(((observableValue, aBoolean, t1) -> {
            initAnimation();
            gameLoop.play();
        }));


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

                ImageView cases = terrainVue.creerTuile(map[l][c]);

                cases.setOnMouseClicked(e -> {
                    if (modePlacement){
                        if (map[ligne][colonne] == 0){

                            double posX = colonne * 32;
                            double posY = ligne * 32;
                            double[] coords = new double[]{posX, posY};

                            if (aPlacer == 100) {
                                Poubelle p = new Poubelle(coords, 100.0, 16.0, gameWorld);
                                ImageView imgPoubelle = terrainVue.creerTour(p);

                                imgPoubelle.setTranslateX(posX);
                                imgPoubelle.setTranslateY(posY);

                                carte.getChildren().add(imgPoubelle);

                                map[ligne][colonne] = p.getpoids();
                            }
                            modePlacement = false;
                            remplirMap();
                        } else {
                            System.out.println("Impossible de placer ici");
                        }
                    }
                });

                /*cases.setOnMouseClicked(e -> {
                    if (modePlacement){
                        if (map[ligne][colonne] == 0){
                            map[ligne][colonne] = aPlacer;
                            modePlacement = false;

                            remplirMap();
                        } else {
                            System.out.println("Impossible de placer ici");
                        }
                    }
                });*/

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
            gameWorld.updateGW(temps*0.017);
            temps++;
        }));
        gameLoop.getKeyFrames().add(kf);
    }

    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remetEnnemiTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("nouveau PouletClassique");

            gameWorld.ajouterAnimal(AnimauxManager.creerPouletClassique(gameWorld));
        } else if (event.getCode() == KeyCode.A) {

            System.out.println("nouveau Racoutou");

            gameWorld.ajouterAnimal(new Racoutou(gameWorld));
        } else if (event.getCode() == KeyCode.R) {

            System.out.println("nouveau PouletBouclier");

            gameWorld.ajouterAnimal(new PouletBouclier(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.M) {

            System.out.println("nouveau PouletMenotte");

            gameWorld.ajouterAnimal(new PouletMenotte(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.C) {

            System.out.println("nouveau ChatClassique");

            gameWorld.ajouterAnimal(AnimauxManager.creerChatClassique(gameWorld));
        } else if (event.getCode() == KeyCode.J) {

            System.out.println("nouveau ChatJournaliste");

            gameWorld.ajouterAnimal(new ChatJournaliste(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.G) {

            System.out.println("nouveau IGPN");

            gameWorld.ajouterAnimal(new ChatScientifique(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.V) {

            System.out.println("nouveau Volant");

            gameWorld.ajouterAnimal(new PouletVolant(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        }


    }

    public double[] getMousePos(Window window) {
        double[] mousePos = new double[1];
        window.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mousePos[0] = mouseEvent.getX();
                mousePos[1] = mouseEvent.getY();
            }
        });
        return mousePos;
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