package app.Controller;

import app.Controller.Listener.EntiteHealthListener;
import app.Controller.Listener.EntitesListListener;
import app.Controller.Listener.*;
import app.Modele.AudioManager;
import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EntitesManager;
import app.Modele.Managers.EnnemisSpawn;
import app.Modele.Managers.MapManager;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Vue.CameraManager;
import app.Vue.EntiteVue;
import app.Vue.ImageSetter;
import app.Vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static app.Controller.MenuController.*;

public class Controller implements Initializable {

    // VUE
    @FXML private BorderPane applicationPane; // border pane parent de tous
    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private TilePane tileMap;

    //Sélection de la map + boutton pour démarrer
    @FXML private Pane mapSelectorPane;
    @FXML private ImageView mapPrevisualisationImageView;   //Le grand en fond
    @FXML private ImageView iconeChoixMapImageView;     //L'icone au milieu
    @FXML private Button selecteurMapGaucheButton;
    @FXML private Button selecteurMapDroitButton;
    @FXML private Label mapNameLabel;
    @FXML private Label difficultyLabel;
    private final MapManager mapManager = new MapManager();
    private int indiceMap = 0;
    private final List<Image[]> mapPrevisualisationAndIconsImages = List.of(
            new Image[]{new Image("/app/maps/images/map1Preview.jpg"), new Image("/app/maps/images/map1Icon.png")},
            new Image[]{new Image("/app/maps/images/map2Preview.jpg"), new Image("/app/maps/images/map2Icon.png")}
    );
    private final List<String> nomsMaps = List.of(
            "Ville",
            "Prairie"
    );

    private final List<String> difficultesMaps = List.of(
            "Facile",
            "Difficile"
    );

    @FXML private Button poubelle;
    @FXML private Label poubellePrixLabel;

    @FXML private Button chatClassique;
    @FXML private Label chatClassiquePrixLabel;

    @FXML private Button chatMedecin;
    @FXML private Label chatMedecinPrixLabel;

    @FXML private Button chatJournaliste;
    @FXML private Label chatJournalistePrixLabel;

    @FXML private Button chatScientifique;
    @FXML private Label chatScientifiquePrixLabel;

    @FXML private Button chatCuisinier;
    @FXML private Label chatCuisinierPrixLabel;

    @FXML private Button pouletIGPN;
    @FXML private Label pouletIGPNPrixLabel;

    @FXML private Button chatHypnotiseur;
    @FXML private Label chatHypnotiseurPrixLabel;


    @FXML private Label coinLabel;

    @FXML private Label waveLabel;
    @FXML private Label waveTimerLabel;

    @FXML private VBox menuPause;

    @FXML private VBox menuReglages;
    @FXML private Button btnSon;
    @FXML private ComboBox<String> comboResolution;
    @FXML private ImageView imgSon;

    private Image imageSonOn;
    private Image imageSonOff;


    @FXML private Pane finJeu;
    @FXML private ImageView imgFinJeu;

    private TerrainVue terrainVue;
    private boolean enPause = false;

    //MODELE
    private GameWorld gameWorld;

    //CONTROLEUR
    private CameraManager cameraManager;
    private Timeline gameLoop;
    private IntegerProperty temps = new SimpleIntegerProperty(0);

    //Listener
    private BooleanProperty isGameStarted = new SimpleBooleanProperty(false);
    private ControleurDeClic clic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initButtonsPlacement();

        //Init terrain
        terrainVue = new TerrainVue();
        terrainVue.delimitationMap(tileMap);

        //Init camera
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();

        //Gestion sélecteur map
        isApplicationPlayButtonPressed.addListener((observableValue, oldV, newV) -> {

            if (newV) {

                indiceMap = 0;

                mapSelectorPane.setVisible(true);
                afficherMapSelectionnee();
            }
        });

        //Gestion lancement / arrêt du jeu
        isGameStarted.addListener(((observableValue, aBoolean, t1) -> {

            if (t1 == true) {
                initAnimation();
                gameLoop.play();
            } else
                gameLoop.stop();
        }));

        //Gestion Clavier
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

        //Audio
        AudioManager.getInstance().jouerMusique("/app/audio/epic.wav");

        imageSonOn = ImageSetter.sonOn;
        imageSonOff = ImageSetter.sonOff;

        AudioManager.getInstance().sonActiveProperty().addListener((obs, ancien, estActive) -> {
            if (estActive) {
                imgSon.setImage(imageSonOn);
            } else {
                imgSon.setImage(imageSonOff);
            }
        });

        //Résolutions
        comboResolution.getItems().addAll(
                "1280 x 720",
                "1366 x 768",
                "1408 x 896",
                "1600 x 900",
                "1920 x 1080"
        );

        comboResolution.setValue("1408 x 896");

        comboResolution.setOnAction(e -> {

            String resolution = comboResolution.getValue();

            String[] parties = resolution.split(" x ");

            double largeur = Integer.parseInt(parties[0]);
            double hauteur = Integer.parseInt(parties[1]);

            applicationPane.getScene().getWindow().setWidth(largeur);
            applicationPane.getScene().getWindow().setHeight(hauteur);

        });
    }

    @FXML private void gameStartButtonPressed() {

        int[][] mapChoisie = mapManager.getMaps().get(indiceMap);

        gameWorld = new GameWorld(mapChoisie);

        terrainVue.remplirMap(tileMap, mapChoisie);

        initialiserGameWorld();

        initAnimation();

        mapSelectorPane.setVisible(false);

        isGameStarted.setValue(true);
    }

    @FXML void mapChangeLeft() {

        indiceMap--;

        if (indiceMap < 0)
            indiceMap = mapPrevisualisationAndIconsImages.size() - 1;

        afficherMapSelectionnee();
    }

    @FXML void mapChangeRight() {

        indiceMap++;

        if (indiceMap >= mapPrevisualisationAndIconsImages.size())
            indiceMap = 0;

        afficherMapSelectionnee();
    }

    @FXML
    private void clicBoutonSon() {
        boolean etatActuel = AudioManager.getInstance().sonActiveProperty().get();

        AudioManager.getInstance().sonActiveProperty().set(!etatActuel);
    }

    @FXML
    private void pause() {

        if (gameWorld == null) return;

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
    private void ouvrirReglages() {
        menuPause.setVisible(false);
        menuReglages.setVisible(true);
    }

    @FXML
    private void retourPause() {
        menuReglages.setVisible(false);
        menuPause.setVisible(true);
    }

    @FXML
    private void redemarrerJeu() {

        gameLoop.stop();

        enPause = false;
        menuPause.setVisible(false);
        finJeu.setVisible(false);

        //On reset le temps de la gameLoop
        temps.setValue(0);

        //Reset du monde
        int[][] mapChoisie = mapManager.getMaps().get(indiceMap);
        gameWorld = new GameWorld(mapChoisie);

        //Reset du visuel
        carte.getChildren().clear();
        tileMap.getChildren().clear();

        //Reconstru du terrain
        terrainVue = new TerrainVue();
        terrainVue.delimitationMap(tileMap);
        terrainVue.remplirMap(tileMap, mapChoisie);

        //Reconstru camera
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();

        //Reset listener et tt
        initialiserGameWorld();

        //Reposition camera
        cameraManager.centrerCarte();
        cameraManager.verifierLimitesCamera();

        initAnimation();
        gameLoop.play();
    }

    private void initialiserGameWorld() {

        initRacoutou();

        gameWorld.getAnimaux().addListener(new EntitesListListener(carte, gameWorld));
        gameWorld.getBarrage().addListener(new EntitesListListener(carte, gameWorld));
        gameWorld.getProjectiles().addListener(new ProjectileListener(carte));

        gameWorld.getTheEnd().addListener((obs, oldV, newV) -> {

            if (newV.intValue() > 0)
                imgFinJeu.setImage(new Image(getClass().getResource("/app/images/gagne.gif").toExternalForm()));
            else if (newV.intValue() < 0)
                imgFinJeu.setImage(new Image(getClass().getResource("/app/images/perdue.gif").toExternalForm()));

            finJeu.setVisible(true);
            isGameStarted.setValue(false);
        });

        clic = new ControleurDeClic(
                carte,
                gamePane,
                gameWorld,
                terrainVue
        );

        carte.addEventHandler(MouseEvent.MOUSE_MOVED, clic);
        carte.addEventHandler(MouseEvent.MOUSE_CLICKED, clic);

        initialiserDragAndDrop();

        coinLabel.textProperty().bind(gameWorld.getTotalCoin().asString());
        waveLabel.textProperty().bind(gameWorld.getNumeroVagueProperty().asString());
        waveTimerLabel.textProperty().bind(gameWorld.getTempsActuelVagueProperty().asString().concat(" / ").concat(gameWorld.getDurreeVagueProperty().asString()));
    }

    private void afficherMapSelectionnee() {

        mapPrevisualisationImageView.setImage(mapPrevisualisationAndIconsImages.get(indiceMap)[0]);
        iconeChoixMapImageView.setImage(mapPrevisualisationAndIconsImages.get(indiceMap)[1]);

        mapNameLabel.setText(nomsMaps.get(indiceMap));
        difficultyLabel.setText(difficultesMaps.get(indiceMap));
    }

    public void initialiserDragAndDrop() {

        DragAndDrop dragImage = new DragAndDrop();
        dragImage.drag(poubelle);
        dragImage.drag(chatClassique);
        dragImage.drag(chatMedecin);
        dragImage.drag(chatJournaliste);
        dragImage.drag(chatScientifique);
        dragImage.drag(chatCuisinier);
        dragImage.drag(pouletIGPN);
        dragImage.drag(chatHypnotiseur);

        dragImage.survole(tileMap);

        //drop
        tileMap.setOnDragDropped(e -> { //réagit quand la souris relache
            Dragboard db = e.getDragboard();
            int id = Integer.parseInt(db.getString());

            //coordonnées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            clic.placerStructure(ligne, colonne, "poubelle");

            e.consume();
            gamePane.requestFocus();
        });
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

            gameWorld.ajouterAnimal(EntitesManager.creerPouletClassique(gameWorld));

        } else if (event.getCode() == KeyCode.X) {

            System.out.println("nouveau Racoutou");

            gameWorld.ajouterAnimal(EntitesManager.creerPouletRolleur(gameWorld));
        }
        else if (event.getCode() == KeyCode.R) {

            System.out.println("nouveau PouletBouclier");

            gameWorld.ajouterAnimal(new PouletBouclier(gameWorld));
        } else if (event.getCode() == KeyCode.M) {

            System.out.println("nouveau PouletMenotte");

            gameWorld.ajouterAnimal(EntitesManager.creerPouletMenotte(gameWorld));
        } else if (event.getCode() == KeyCode.C) {

            System.out.println("nouveau ChatClassique");

            gameWorld.ajouterAnimal(EntitesManager.creerChatClassique(gameWorld));
        } else if (event.getCode() == KeyCode.J) {

            System.out.println("nouveau ChatJournaliste");

            gameWorld.ajouterAnimal(EntitesManager.creerChatJournaliste(gameWorld));
        } else if (event.getCode() == KeyCode.Y) {

            System.out.println("nouveau scientifique");
            gameWorld.ajouterAnimal(new ChatScientifique(gameWorld.getRacoutou().getCoord(), gameWorld));

        } else if (event.getCode() == KeyCode.G) {

            System.out.println("nouveau igpn");
            gameWorld.ajouterAnimal(new PouletIGPN(EnnemisSpawn.randomCoord(gameWorld), gameWorld));

        }else if (event.getCode() == KeyCode.K) {

            System.out.println("nouveau hypno");
            gameWorld.ajouterAnimal(new ChatHypnotiseur(new double[]{gameWorld.getRacoutou().getX()+20, gameWorld.getRacoutou().getY()-20} , gameWorld));

        } else if (event.getCode() == KeyCode.H) {

            System.out.println("nouveau medecin");
            gameWorld.ajouterAnimal(EntitesManager.creerChatMedecin(gameWorld));

        } else if (event.getCode() == KeyCode.B) {

            System.out.println("nouveau ChatCuisinier");
            gameWorld.ajouterAnimal(EntitesManager.creerChatCuisinier(gameWorld));

        }else if (event.getCode() == KeyCode.W) {

            System.out.println("nouveau PouletConservateur");
            gameWorld.ajouterAnimal(EntitesManager.creerPouletConservateur(gameWorld));

        } else if (event.getCode() == KeyCode.L) {

            System.out.println("nouveau pSoigne");
            gameWorld.ajouterAnimal(EntitesManager.creerPouletConservateur(gameWorld));
        }

        if (event.getCode() == KeyCode.ENTER){
            gameWorld.setTotalCoin(1000);
        } else if (event.getCode() == KeyCode.A) {
            gameWorld.getRacoutou().estAttaque(1);
        }
    }

    private void initRacoutou(){
        Entite racoutou = gameWorld.getRacoutou();
       ImageView imgRacoutou = EntiteVue.appliquerBonneImage(racoutou, true);
        carte.getChildren().add(imgRacoutou);

        racoutou.getHealthProperty().addListener(new EntiteHealthListener(carte, racoutou));
        VieControlleur barreVie = new VieControlleur(racoutou, imgRacoutou);
        StackPane visuelBarre = barreVie.getConteneur();
        visuelBarre.setId("visuBarre"+racoutou.getId());
        carte.getChildren().add(visuelBarre);

        InfoBulleListener infoBulleListener = new InfoBulleListener(carte, gameWorld, racoutou);
        imgRacoutou.setOnMouseClicked(event -> {
            infoBulleListener.changeAfficherDescription();
        });
        infoBulleListener.ajoutZoneDescription();

    }

    //REPETITION !!!
    @FXML
    public void lancerJeu() throws IOException {

        Pane jeu = FXMLLoader.load(MenuController.class.getResource("/app/main.fxml"));
        applicationPane.getScene().setRoot(jeu);
        isGameStarted.setValue(true);

    }

    public void initButtonsPlacement(){
        /*
        boutonsPlacement = FXCollections.observableList(new ArrayList<>());;
        boutonsPlacement.add(poubelle);
        boutonsPlacement.add(chatClassique);
        boutonsPlacement.add(chatJournaliste);
        boutonsPlacement.add(chatScientifique);
        boutonsPlacement.add(chatMedecin);

         */

        EventHandler<ActionEvent> boutonsshop = actionEvent -> {

            if (gameWorld == null) return;  //Si on a pas encore lancé la partie pr éviter les affichages d'erreur de "clic est null !"

            clic.setModePlacement(true);
            clic.setName(((Button) (actionEvent.getSource())).getId());
        };
        poubelle.setOnAction(boutonsshop);
        poubellePrixLabel.setText(StatsEntiteInitialiser.getStatsLevels(poubelle.getId()).getFirst()[1].toString());

        chatJournaliste.setOnAction(boutonsshop);
        chatJournalistePrixLabel.setText(String.valueOf(((int) (StatsEntiteInitialiser.getStatsLevels(chatJournaliste.getId()).getFirst()[1]))));

        chatMedecin.setOnAction(boutonsshop);
        chatMedecinPrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(chatMedecin.getId()).getFirst()[1])));

        chatClassique.setOnAction(boutonsshop);
        chatClassiquePrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(chatClassique.getId()).getFirst()[1])));

        chatScientifique.setOnAction(boutonsshop);
        chatScientifiquePrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(chatScientifique.getId()).getFirst()[1])));

        chatCuisinier.setOnAction(boutonsshop);
        chatCuisinierPrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(chatCuisinier.getId()).getFirst()[1])));

        pouletIGPN.setOnAction(boutonsshop);
        pouletIGPNPrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(pouletIGPN.getId()).getFirst()[1])));

        chatHypnotiseur.setOnAction(boutonsshop);
        chatHypnotiseurPrixLabel.setText(String.valueOf(((int) StatsEntiteInitialiser.getStatsLevels(chatHypnotiseur.getId()).getFirst()[1])));

    }



}