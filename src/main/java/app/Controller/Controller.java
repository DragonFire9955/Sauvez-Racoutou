package app.Controller;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.Buffer.ChatCuisinier;
import app.Modele.Entites.Animaux.Specialise.Buffer.PouletConservateur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Terrain;
import app.Modele.Vague;
import app.Vue.CameraManager;
import app.Vue.EntiteVue;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static app.Controller.MenuController.*;

public class Controller implements Initializable {

    // VUE
    @FXML
    private Pane gamePane;
    @FXML
    private Pane carte;
    @FXML
    private TilePane tileMap;

    @FXML
    private Button btnPoubelle;
    @FXML
    private Button btnClassique;
    @FXML
    private Button btnSniper;
    @FXML
    private Button btnJournaliste;


    @FXML
    private Label coinLabel;
    @FXML
    private Label waveLabel;
    @FXML
    private Label waveTimerLabel;
    @FXML
    private VBox menuPause;

    private TerrainVue terrainVue;
    private boolean enPause = false;

    private boolean modePlacement = false;
    private int idEntite = -1;

    private ControleurDeClic clicControl;

    //MODELE
    private int[][] map;
    private GameWorld gameWorld;

    //CONTROLEUR
    private CameraManager cameraManager;
    private Timeline gameLoop;
    private IntegerProperty temps = new SimpleIntegerProperty(0);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gameWorld = new GameWorld();
        this.map = gameWorld.getMap();

        terrainVue = new TerrainVue();
        clicControl = new ControleurDeClic(idEntite, modePlacement, gameWorld, terrainVue, carte, gamePane);

        terrainVue.delimitationMap(tileMap);
        terrainVue.remplirMap(tileMap, map, clicControl);

        DragAndDrop dragImage = new DragAndDrop();
        dragImage.drag(btnPoubelle, 100, "/app/images/poubelle.png");
        dragImage.drag(btnClassique, 101, "/app/images/chat.png");
        dragImage.drag(btnSniper, 102, "/app/images/sniper.png");
        dragImage.drag(btnJournaliste, 103, "/app/images/journaliste.png");

        dragImage.survole(tileMap);

        //drop
        tileMap.setOnDragDropped(e -> { //réagit quand la souris relache
            Dragboard db = e.getDragboard();
            int id = Integer.parseInt(db.getString());

            //coordonnées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            clicControl.placerStructure(ligne, colonne, id);

            e.consume();
        });

        //Initialisation des Managers
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> gameLoop.stop());
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();

        //Binding du label coin, à déplacer au bon endroit
        coinLabel.textProperty().bind(gameWorld.getTotalCoin().asString());

        //Binding label vague + timerVague
        waveLabel.textProperty().bind(Vague.currentWave.asString());
        waveTimerLabel.textProperty().bind(temps.multiply(0.017).asString("%.0f / " + Vague.waveDuration));

        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new EntitesListListener(carte, gameWorld));

        //IMAGE DE RACOUTOU
        initRacoutou();

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
                if (event.getCode() == KeyCode.ESCAPE){
                    pause();
                }

                remetEnnemiTest(event);
            });
        });

    }

    @FXML
    private void pause() {
        if (enPause) {
            gameLoop.play();
            menuPause.setVisible(false);
            enPause = false;
        } else {
            gameLoop.pause();
            menuPause.setVisible(true);
            enPause = true;
        }
        gamePane.requestFocus();
    }

    @FXML
    private void quitterJeu() {
        gameLoop.stop();
        gamePane.getScene().setRoot(menu);
        isGameStarted.setValue(false);
    }

    @FXML
    private void redemarrerJeu() {
        gameLoop.stop();
        temps.setValue(0);
        enPause = false;
        menuPause.setVisible(false);

        gameWorld.getAnimaux().clear();
        gameWorld.getTotalCoin().set(50);

        carte.getChildren().removeIf(node -> node != tileMap);
        //supprime si l'element est différent de la tileMap (on garde juste elle)

        map = Terrain.genererMap();
        terrainVue.delimitationMap(tileMap);
        terrainVue.remplirMap(tileMap, map, clicControl);

        gameWorld.ajouterAnimal(new Racoutou(gameWorld));
        gameWorld.ajouterAnimal(AnimauxManager.creerPouletClassique(gameWorld));

        gameLoop.play();
        gamePane.requestFocus();
    }

    @FXML
    private void placerPoubelle(){
        clicControl.setModePlacement(true);
        clicControl.setIdEntite(100);
    }

    @FXML
    private void placerChatClassique() {
        clicControl.setModePlacement(true);
        clicControl.setIdEntite(101);
    }

    @FXML
    private void placerChatSniper() {
        clicControl.setModePlacement(true);
        clicControl.setIdEntite(102);
    }

    @FXML
    private void placerChatJournaliste() {
        clicControl.setModePlacement(true);
        clicControl.setIdEntite(103);
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.017),(ev ->{
            gameWorld.updateGW(temps.getValue()*0.017);
            temps.setValue(temps.getValue()+1);
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

            gameWorld.ajouterAnimal(AnimauxManager.creerPouletMenotte(gameWorld));
        } else if (event.getCode() == KeyCode.C) {

            System.out.println("nouveau ChatClassique");

            gameWorld.ajouterAnimal(AnimauxManager.creerChatClassique(gameWorld));
        } else if (event.getCode() == KeyCode.J) {

            System.out.println("nouveau ChatJournaliste");

            gameWorld.ajouterAnimal(AnimauxManager.creerChatJournaliste(gameWorld));
        }
        else if (event.getCode() == KeyCode.G) {

            System.out.println("nouveau scientifique");

            gameWorld.ajouterAnimal(new ChatScientifique(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.B) {

            System.out.println("nouveau ChatCuisinier");
            double[] coord = gameWorld.getRacoutou().getCoord();
            coord[0] +=10;
            coord[1]+=10;
            gameWorld.ajouterAnimal(new ChatCuisinier(coord, gameWorld));
        } else if (event.getCode() == KeyCode.V) {

            System.out.println("nouveau Volant");

            gameWorld.ajouterAnimal(new PouletVolant(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        } else if (event.getCode() == KeyCode.L) {

            System.out.println("nouveau pSoigne");

            gameWorld.ajouterAnimal(new PouletConservateur(EnnemisSpawn.randomCoord(gameWorld), gameWorld));
        }



    }

    private void initRacoutou(){
        carte.getChildren().add(EntiteVue.appliquerBonneImage(gameWorld.getRacoutou()));
        VieControlleur barreVie = new VieControlleur(gameWorld.getRacoutou());
        StackPane visuelBarre = barreVie.getConteneur();
        visuelBarre.setId(gameWorld.getRacoutou().getId());
        carte.getChildren().add(visuelBarre);
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