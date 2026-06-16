package app.Controller;

import app.Controller.Listener.EntiteHealthListener;
import app.Controller.Listener.EntitesListListener;
import app.Controller.Listener.*;
import app.Modele.AudioManager;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.AlterationElementaire.ChatScientifique;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Ruchien;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static app.Controller.ControlleurMethodesMultiUsages.actualiserTitreMusique;
import static app.Controller.MenuController.*;

public class Controller implements Initializable {

    // VUE
    @FXML private BorderPane applicationPane; // border pane parent de tous
    @FXML private VBox shopVBox;
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
            new Image[]{new Image("/app/maps/images/map2Preview.jpg"), new Image("/app/maps/images/map2Icon.png")},
            new Image[]{new Image("/app/maps/images/map3Preview.png"), new Image("/app/maps/images/map3Icon.png")},
            new Image[]{new Image("/app/maps/images/map4Preview.png"), new Image("/app/maps/images/map4Icon.png")},
            new Image[]{new Image("/app/maps/images/map5Preview.png"), new Image("/app/maps/images/map5Icon.png")}
    );
    private final List<String> nomsMaps = List.of(
            "Straight City",
            "Middle City",
            "Square City",
            "Circle City",
            "Free City"

    );

    private final List<String> difficultesMaps = List.of(
            " Très Facile",
            " Facile",
            " Moyen",
            "Difficile",
            "Très Difficile"
    );

    @FXML private HBox vieRacoutou;

    @FXML private Button poubelle;
    @FXML private ImageView poubelleShopImageView;
    @FXML private Label poubellePrixLabel;

    @FXML private Button chatClassique;
    @FXML private ImageView chatClassiqueShopImageView;
    @FXML private Label chatClassiquePrixLabel;

    @FXML private Button chatMedecin;
    @FXML private ImageView chatMedecinShopImageView;
    @FXML private Label chatMedecinPrixLabel;

    @FXML private Button chatJournaliste;
    @FXML private ImageView chatJournalisteShopImageView;
    @FXML private Label chatJournalistePrixLabel;

    @FXML private Button chatScientifique;
    @FXML private ImageView chatScientifiqueShopImageView;
    @FXML private Label chatScientifiquePrixLabel;

    @FXML private Button chatCuisinier;
    @FXML private ImageView chatCuisiniShopImageView;
    @FXML private Label chatCuisinierPrixLabel;

    @FXML private Button pouletIGPN;
    @FXML private ImageView pouletIGPNShopImageView;
    @FXML private Label pouletIGPNPrixLabel;

    @FXML private Button chatHypnotiseur;
    @FXML private ImageView chatHypnotiseurShopImageView;
    @FXML private Label chatHypnotiseurPrixLabel;


    @FXML private Label coinLabel;

    @FXML private Pane wavePane;
    @FXML private Label waveLabel;
    @FXML private Label waveTimerLabel;

    @FXML private VBox menuPause;

    @FXML private VBox menuReglages;
    @FXML private ComboBox<String> comboResolution;
    @FXML private ImageView imgSon;
    @FXML private Slider sliderVolume;
    @FXML private Label labelTitreMusique;

    private Image imageSonOn;
    private Image imageSonOff;

    @FXML private Label racoutouHpLabel;
    @FXML private ProgressBar racoutouPvBar;

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

        EntitesManager.initNivBaseLorsAchat();

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

                EntitesManager.initNivBaseLorsAchat();
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
;
            });
        });

        //Résolutions
        comboResolution.getItems().addAll(
                "1280 x 720",
                "1366 x 768",
                "1408 x 900",
                "1600 x 1000",
                "1920 x 1080"
        );

        comboResolution.setValue("1408 x 900");

        comboResolution.setOnAction(e -> {

            String resolution = comboResolution.getValue();

            String[] parties = resolution.split(" x ");

            double largeur = Integer.parseInt(parties[0]);
            double hauteur = Integer.parseInt(parties[1]);

            applicationPane.getScene().getWindow().setWidth(largeur);
            applicationPane.getScene().getWindow().setHeight(hauteur);

        });

        //Audio
        AudioManager.getInstance().jouerMusique(AudioManager.getInstance().getMusiqueActuelle());

        ControlleurMethodesMultiUsages.actualiserTitreMusique(labelTitreMusique);

        imageSonOn = ImageSetter.sonOn;
        imageSonOff = ImageSetter.sonOff;

        AudioManager.getInstance().sonActiveProperty().addListener((obs, ancien, estActive) -> {
            if (estActive) {
                imgSon.setImage(imageSonOn);
            } else {
                imgSon.setImage(imageSonOff);
            }
        });

        sliderVolume.valueProperty().bindBidirectional(AudioManager.getInstance().volumeProperty());
    }

    @FXML private void gameStartButtonPressed() {

        int[][] mapChoisie = mapManager.getMaps().get(indiceMap);

        gameWorld = new GameWorld(mapChoisie);

        terrainVue.remplirMap(tileMap, mapChoisie);

        initialiserGameWorld();

        mapSelectorPane.setVisible(false);

        isGameStarted.setValue(true);

        vieRacoutou.setVisible(true);

        wavePane.setVisible(true);

        gameLoop.playFromStart();
    }





    @FXML
    private void musiquePrecedente() {
        ControlleurMethodesMultiUsages.musiquePrecedente(labelTitreMusique);
    }

    @FXML
    private void musiqueSuivante() {
        ControlleurMethodesMultiUsages.musiqueSuivante(labelTitreMusique);
    }

    @FXML
    private void clicBoutonSon() {
        ControlleurMethodesMultiUsages.clicBoutonSon();
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
        isApplicationPlayButtonPressed.set(false);
    }

    @FXML
    private void ouvrirReglages() {
        menuPause.setVisible(false);
        ControlleurMethodesMultiUsages.setVisibleReglages(menuReglages);
    }

    @FXML
    private void retourPause() {
        ControlleurMethodesMultiUsages.setVisibleReglages(menuReglages);
        menuPause.setVisible(true);
    }

    @FXML
    private void redemarrerJeu() {

        gameLoop.stop();
        gameWorld.getBarrage().clear();
        int[][] map = gameWorld.getMap();
        enPause = false;
        menuPause.setVisible(false);
        finJeu.setVisible(false);

        //On reset le temps de la gameLoop
        temps.setValue(0);
        carte.getChildren().clear();
        gameStartButtonPressed();
        carte.getChildren().addFirst(tileMap);
        initRacoutou();


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

        coinLabel.textProperty().bind((gameWorld.getTotalCoin().asString()));
        waveLabel.textProperty().bind((gameWorld.getNumeroVagueProperty().asString()));
        waveTimerLabel.textProperty().bind((gameWorld.getTempsActuelVagueProperty().asString().concat(" / ").concat(gameWorld.getDurreeVagueProperty().asString())));

    }

    private void afficherMapSelectionnee() {

        mapPrevisualisationImageView.setImage(mapPrevisualisationAndIconsImages.get(indiceMap)[0]);
        mapPrevisualisationImageView.setFitWidth(mapPrevisualisationAndIconsImages.get(indiceMap)[0].getWidth()*.87);
        mapPrevisualisationImageView.setFitHeight(mapPrevisualisationAndIconsImages.get(indiceMap)[0].getHeight()*.87);

        iconeChoixMapImageView.setImage(mapPrevisualisationAndIconsImages.get(indiceMap)[1]);

        mapNameLabel.setText(String.valueOf(nomsMaps.get(indiceMap)));
        difficultyLabel.setText(String.valueOf(difficultesMaps.get(indiceMap)));
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
            String nomStructure = db.getString();

            //coordonnées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            clic.placerStructure(ligne, colonne, nomStructure);

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

        if (event.getCode() == KeyCode.ENTER){
            gameWorld.setTotalCoin(1000);
        } else if (event.getCode() == KeyCode.A) {
            gameWorld.getRacoutou().estAttaque(1);
        }
    }

    private void initRacoutou(){

        Entite racoutou = gameWorld.getRacoutou();
        ImageView imgRacoutou = EntiteVue.creerBonneImage(racoutou);
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


        double pvMax = racoutou.getMaxHealth();
        double pvActuels = racoutou.getHealthProperty().get();

        racoutouPvBar.setProgress(pvActuels / pvMax);
        racoutouHpLabel.setText((int)pvActuels + " / " + (int)pvMax + " PV");

        racoutou.getHealthProperty().addListener((observable, oldValue, newValue) -> {
            double pvActuel = newValue.doubleValue();

            racoutouPvBar.setProgress(Math.max(0, pvActuel / pvMax));

            racoutouHpLabel.setText((int)Math.max(0, pvActuel) + " / " + (int)pvMax + " PV");
        });

    }

    @FXML
    public void lancerJeu() throws IOException {
        ControlleurMethodesMultiUsages.lancerJeu(applicationPane, isGameStarted);
    }

    public void initButtonsPlacement(){

        EventHandler<ActionEvent> boutonsshop = actionEvent -> {

            if (gameWorld == null) return;  //Si on a pas encore lancé la partie pr éviter les affichages d'erreur de "clic est null !"

            clic.setModePlacement(true);
            clic.setName(((Button) (actionEvent.getSource())).getId());
            System.out.println("ACTION: " + ((Button) (actionEvent.getSource())).getId());
        };

        poubelle.setOnAction(boutonsshop);
        poubellePrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(poubelle.getId()), poubelle.getId())));
        chatJournaliste.setOnAction(boutonsshop);
        chatJournalistePrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatJournaliste.getId()), chatJournaliste.getId())));
        chatMedecin.setOnAction(boutonsshop);
        chatMedecinPrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatMedecin.getId()), chatMedecin.getId())));
        chatClassique.setOnAction(boutonsshop);
        chatClassiquePrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatClassique.getId()), chatClassique.getId())));
        chatScientifique.setOnAction(boutonsshop);
        chatScientifiquePrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatScientifique.getId()), chatScientifique.getId())));
        chatCuisinier.setOnAction(boutonsshop);
        chatCuisinierPrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatCuisinier.getId()), chatCuisinier.getId())));
        pouletIGPN.setOnAction(boutonsshop);
        pouletIGPNPrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(pouletIGPN.getId()), pouletIGPN.getId())));
        chatHypnotiseur.setOnAction(boutonsshop);
        chatHypnotiseurPrixLabel.setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(chatHypnotiseur.getId()), chatHypnotiseur.getId())));
    }

    //Partie Shop
    @FXML public void acheterEvoPlus(ActionEvent event) {

        String id = ((Button) event.getSource()).getId().replace("LvlRightButton", "").replace("LvlLeftButton", "");
        EntitesManager.incrementerNivBaseLorsAchat(id);
        ((ImageView) shopVBox.lookup("#"+id+"ShopImageView")).setImage(new Image ("/app/images/"+ id +"/niv"+ EntitesManager.niveauDeBasesLorsAchat.get(id)+"/img.png"));
        ((Label) shopVBox.lookup("#"+id+"PrixLabel")).setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(id), id)));
    }

    @FXML public void acheterEvoMoins(ActionEvent event) {

        String id = ((Button) event.getSource()).getId().replace("LvlLeftButton", "").replace("LvlRightButton", "");
        EntitesManager.decrementerNivBaseLorsAchat(id);
        ((ImageView) shopVBox.lookup("#"+id+"ShopImageView")).setImage(new Image ("/app/images/"+ id +"/niv"+  EntitesManager.niveauDeBasesLorsAchat.get(id) +"/img.png"));
        ((Label) shopVBox.lookup("#"+id+"PrixLabel")).setText(String.valueOf(EntitesManager.getTotalCoinUpgradeProperty(EntitesManager.niveauDeBasesLorsAchat.get(id), id)));
    }
}