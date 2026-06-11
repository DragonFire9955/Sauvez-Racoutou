package app.Controller;

import app.Controller.Listener.*;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Modele.Terrain;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Vue.CameraManager;
import app.Vue.EntiteVue;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.Controller.MenuController.*;

public class Controller implements Initializable {

    // VUE
    @FXML
    private BorderPane applicationPane; // border pane parent de tous
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
    private Button btnMedecin;
    @FXML
    private Button btnJournaliste;
    @FXML
    private Button btnScientifique;


    @FXML
    private Label coinLabel;
    @FXML
    private Label waveLabel;
    @FXML
    private Label waveTimerLabel;
    @FXML
    private VBox menuPause;


    @FXML
    private Pane finJeu;
    @FXML
    private ImageView imgFinJeu;

    private TerrainVue terrainVue;
    private boolean enPause = false;

    //MODELE
    private int[][] map;
    private GameWorld gameWorld;

    //CONTROLEUR
    private CameraManager cameraManager;
    private Timeline gameLoop;
    private IntegerProperty temps = new SimpleIntegerProperty(0);

    //Listener
    private ControleurDeClic clic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gameWorld = new GameWorld();
        this.map = gameWorld.getMap();

        terrainVue = new TerrainVue();
        terrainVue.delimitationMap(tileMap);

        terrainVue.remplirMap(tileMap, map);

        DragAndDrop dragImage = new DragAndDrop();
        dragImage.drag(btnPoubelle, 100, "/app/images/poubelle.png");
        dragImage.drag(btnClassique, 101, "/app/images/chatClassique/niv0/img.png");
        dragImage.drag(btnJournaliste, 103, "/app/images/chatJournaliste/niv0/img.png");
        dragImage.drag(btnMedecin, 102, "/app/images/chatMedecin.png");
        dragImage.drag(btnJournaliste, 103, "/app/images/chatJournaliste.png");
        dragImage.drag(btnScientifique, 104, "/app/images/chatScientifique.png");

        dragImage.survole(tileMap);

        //drop
        tileMap.setOnDragDropped(e -> { //réagit quand la souris relache
            Dragboard db = e.getDragboard();
            int id = Integer.parseInt(db.getString());

            //coordonnées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            clic.placerStructure(ligne, colonne, id);

            e.consume();
        });

        //Initialisation des Managers
        gameWorld = new GameWorld();
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> {

            if(newV.intValue()>0){
                imgFinJeu.setImage(new Image("app/images/gagne.gif"));
            } else if (newV.intValue()<0) {
                imgFinJeu.setImage(new Image("app/images/gif/perdue.gif"));
            }
            imgFinJeu.setFitWidth(300);
            imgFinJeu.setFitHeight(300);
            imgFinJeu.setPreserveRatio(true);
            imgFinJeu.setSmooth(true);
            imgFinJeu.setCache(true);
            finJeu.setVisible(true);
            isGameStarted.setValue(false);
        });
        /*
        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> {
            if(newV == true) {
                isGameStarted.setValue(false);
                System.out.println(isGameStarted);
                applicationPane.getScene().setRoot(menu);
                System.out.println(applicationPane);
            }
        });

         */
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();

        //Observable eventListener
        clic = new ControleurDeClic(carte, gamePane, gameWorld, terrainVue);
        carte.addEventHandler(MouseEvent.MOUSE_MOVED, clic);
        carte.addEventHandler(MouseEvent.MOUSE_CLICKED, clic);

        //Binding du label coin, à déplacer au bon endroit
        coinLabel.textProperty().bind(gameWorld.getTotalCoin().asString());

        //Binding label vague + timerVague

        waveLabel.textProperty().bind(gameWorld.getNumeroVagueProperty().asString());



        waveTimerLabel.textProperty().bind(gameWorld.getTempsActuelVagueProperty().asString().concat(" / ").concat(gameWorld.getDurreeVagueProperty().asString()));


        //TEMPORAIRE, A DELET
        gameWorld.getAnimaux().addListener(new EntitesListListener(carte, gameWorld));
        for (Entite e : gameWorld.getAnimaux()) {
            e.getHealthProperty().addListener((observableValue, oldV, newV) -> {
                if (oldV.doubleValue() < newV.doubleValue()) {
                    Node entite = carte.lookup("#" + e.getId());
                    //((ImageView) entite).setImage("URL NOUVELLE IMAGE AVEC DMG");
                }
            });
        }

        //IMAGE DE RACOUTOU
        initRacoutou();
        /*
        gameWorld.getAnimaux().getFirst().getAliveProperty().addListener((observable, oldValue, newValue) -> {
                    gamePane.getScene().setRoot(menu);
                    isGameStarted.setValue(false);
                }
        );

         */
        gameWorld.ajouterAnimal(AnimauxManager.creerPouletClassique(gameWorld));
        System.out.println(isGameStarted);
        isGameStarted.addListener(((observableValue, aBoolean, t1) -> {

            if (t1 == true) {
                initAnimation();
                gameLoop.play();
            } else
                gameLoop.stop();
        }));


        applicationPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            gamePane.requestFocus();
            gamePane.setFocusTraversable(true);

            //On met tout les évènements claviers
            applicationPane.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE){
                    pause();
                } else if (event.getCode() == KeyCode.ENTER){
                    gameWorld.setTotalCoin((gameWorld.getTotalCoin().get() + 10));
                } else if (event.getCode() == KeyCode.DELETE){
                    gameWorld.setTotalCoin((gameWorld.getTotalCoin().get() - 10));
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
        applicationPane.getScene().setRoot(menu);
        isGameStarted.setValue(false);
    }

    @FXML
    private void redemarrerJeu() {
        gameLoop.stop();
        temps.setValue(0);
        enPause = false;
        menuPause.setVisible(false);

        gameWorld.getAnimaux().clear();
        gameWorld.getBarrage().clear();
        gameWorld.getTotalCoin().set(50);

        //carte.getChildren().removeIf(node -> node != tileMap);

        //supprime si l'element est différent de la tileMap (on garde juste elle)


        gameWorld.ajouterAnimal(new Racoutou(gameWorld, StatsEntiteInitialiser.getStatsLevels("Racoutou")));
        initRacoutou();
        gameWorld.ajouterAnimal(AnimauxManager.creerPouletClassique(gameWorld));

        gameLoop.play();
        gamePane.requestFocus();
    }

    @FXML
    private void placerPoubelle(){
        clic.setModePlacement(true);
        clic.setIdEntite(100);
    }

    @FXML
    private void placerChatClassique() {
        clic.setModePlacement(true);
        clic.setIdEntite(101);
    }

    @FXML
    private void placerChatMedecin() {
        clic.setModePlacement(true);
        clic.setIdEntite(102);
    }

    @FXML
    private void placerChatJournaliste() {
        clic.setModePlacement(true);
        clic.setIdEntite(103);
    }

    @FXML
    private void placerChatScientifique() {
        clic.setModePlacement(true);
        clic.setIdEntite(104);
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

        } else if (event.getCode() == KeyCode.X) {

            System.out.println("nouveau Racoutou");

            gameWorld.ajouterAnimal(AnimauxManager.creerPouletRolleur(gameWorld));
        }
        else if (event.getCode() == KeyCode.A) {

            System.out.println("nouveau Racoutou");

            gameWorld.ajouterAnimal(new Racoutou(gameWorld, StatsEntiteInitialiser.getStatsLevels("racoutou")));
        } else if (event.getCode() == KeyCode.R) {

            System.out.println("nouveau PouletBouclier");

            gameWorld.ajouterAnimal(new PouletBouclier(gameWorld));
        } else if (event.getCode() == KeyCode.M) {

            System.out.println("nouveau PouletMenotte");

            gameWorld.ajouterAnimal(AnimauxManager.creerPouletMenotte(gameWorld));
        } else if (event.getCode() == KeyCode.C) {

            System.out.println("nouveau ChatClassique");

            gameWorld.ajouterAnimal(AnimauxManager.creerChatClassique(gameWorld));
        } else if (event.getCode() == KeyCode.J) {

            System.out.println("nouveau ChatJournaliste");

            gameWorld.ajouterAnimal(AnimauxManager.creerChatJournaliste(gameWorld));
        } else if (event.getCode() == KeyCode.Y) {

            System.out.println("nouveau scientifique");
            gameWorld.ajouterAnimal(new ChatScientifique(EnnemisSpawn.randomCoord(gameWorld), gameWorld));

        } else if (event.getCode() == KeyCode.G) {

            System.out.println("nouveau igpn");
            gameWorld.ajouterAnimal(new PouletIGPN(EnnemisSpawn.randomCoord(gameWorld), gameWorld));

        }else if (event.getCode() == KeyCode.K) {

            System.out.println("nouveau hypno");
            gameWorld.ajouterAnimal(new ChatHypnotiseur(new double[]{gameWorld.getRacoutou().getX()+20, gameWorld.getRacoutou().getY()-20} , gameWorld));

        } else if (event.getCode() == KeyCode.H) {

            System.out.println("nouveau medecin");
            gameWorld.ajouterAnimal(AnimauxManager.creerChatMedecin(gameWorld));

        } else if (event.getCode() == KeyCode.B) {

            System.out.println("nouveau ChatCuisinier");
            gameWorld.ajouterAnimal(AnimauxManager.creerChatCuisinier(gameWorld));

        }else if (event.getCode() == KeyCode.W) {

            System.out.println("nouveau PouletConservateur");
            gameWorld.ajouterAnimal(AnimauxManager.creerPouletConservateur(gameWorld));

        }else if (event.getCode() == KeyCode.V) {

            System.out.println("nouveau Volant");

            gameWorld.ajouterAnimal(new PouletVolant(gameWorld));
        } else if (event.getCode() == KeyCode.L) {

            System.out.println("nouveau pSoigne");
            gameWorld.ajouterAnimal(AnimauxManager.creerPouletConservateur(gameWorld));
        }



    }

    private void initRacoutou(){
        Entite racoutou = gameWorld.getRacoutou();
        carte.getChildren().add(EntiteVue.appliquerBonneImage(racoutou, true));
        racoutou.getHealthProperty().addListener(new EntiteHealthListener(carte, racoutou));
        VieControlleur barreVie = new VieControlleur(racoutou);
        StackPane visuelBarre = barreVie.getConteneur();
        visuelBarre.setId(racoutou.getId());
        carte.getChildren().add(visuelBarre);
    }

    //REPETITION !!!
    @FXML
    public void lancerJeu() throws IOException {

        Pane jeu = FXMLLoader.load(MenuController.class.getResource("/app/main.fxml"));
        applicationPane.getScene().setRoot(jeu);
        isGameStarted.setValue(true);

    }



}