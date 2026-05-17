package app.Managers;

import app.Entites.Animaux;
import app.Entites.AnimauxFolder.Allies.ChatClassique;
import app.Entites.AnimauxFolder.Allies.Racoutou;
import app.Entites.AnimauxFolder.Ennemis.PouletClassique;
//import app.Entites.AnimauxFolder.Ennemis.PouletConservateur;
import app.Terrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane gamePane;
    @FXML private Pane carte;
    @FXML private Pane demarragePane;

    @FXML private TilePane tileMap;
    private int[][] map;

    //Là je met tous les Managers, ils gèrent des parties importantes du code répartie en différents Manager pour bien trier.
    private AnimauxManager animauxManager;
    private CameraManager cameraManager;

    private Timeline gameLoop;
    private int temps;
    private boolean jeuLance;

    private Image vert = new Image(getClass().getResourceAsStream("/app/images/vert.png"), 32, 32, true, true);
    private Image marron = new Image(getClass().getResourceAsStream("/app/images/marron.png"), 32, 32, true, true);
    private Image beige = new Image(getClass().getResourceAsStream("/app/images/beige.png"), 32, 32, true, true);

    private Map<Animaux, ImageView> animauxVue = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jeuLance = false;

        carte.setVisible(false);
        carte.setDisable(true);

        this.map = Terrain.genererMap();
        Terrain.delimitationMap(tileMap);

        Terrain.couleurMap(tileMap, map, vert, marron, beige);

        //Initialisation des Managers
        animauxManager = new AnimauxManager();
        cameraManager = new CameraManager(gamePane, carte, tileMap);
        cameraManager.initialiserCamera();


        //TEMPORAIRE, A DELET
        animauxManager.addAnimal(new Racoutou());
        animauxManager.addAnimal(new PouletClassique());

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

                        animauxManager.update(temps);
                        affichageAnimaux();
                        cleanupViews();

                        vaVers(animauxManager.getRacoutou(), animauxManager.getLast());    //LE GETLAST EST TEMPORAIRE.
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void lancerJeu() {

        demarragePane.setVisible(false);
        demarragePane.setDisable(true);
        carte.setVisible(true);

        jeuLance = true;
    }

    private void vaVers(Animaux a1, Animaux a2) {

        double dx = a1.getX() - a2.getX();
        double dy = a1.getY() - a2.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {return;}

        dx /= dist;
        dy /= dist;

        a2.setX(a2.getX() + dx * a2.getVitesse());
        a2.setY(a2.getY() + dy * a2.getVitesse());
    }

    //Fonction de test, uniquement pour les tests, A SUPPRIMER PLUS TARD
    private void remetEnnemiTest(KeyEvent event) {

        if (event.getCode() == KeyCode.E) {

            System.out.println("nouveau PouletClassique");

            animauxManager.addAnimal(new PouletClassique());
        }
    }


    //Partie render des Animaux sur la scène
    private void affichageAnimaux() {

        for (Animaux a: animauxManager.getAnimaux()) {

            ImageView imageView = animauxVue.get(a);

            appliquerBonneImage(a, imageView);
        }
    }

    private void appliquerBonneImage(Animaux animal, ImageView imageView) {

        if (imageView == null) {
            imageView = new ImageView();

            if (animal instanceof Racoutou) {

                imageView.setImage(new Image(getClass().getResourceAsStream("/app/images/racoutou.jpg")));
            } else if (animal instanceof PouletClassique) {

                imageView.setImage(new Image(getClass().getResourceAsStream("/app/images/pioupiouPolicier.jpg")));
            /*    if (animal instanceof PouletConservateur);
                /*imageView.setImage();*/   //On lui met l'image du poulet Conservateur
                //On fait de même pour tout

            //} else if (animal instanceof ChatClassique) {

                //On fait de même pour le chat
            }   //Si il y en a d'autre à ajouter on continue sinon on remplace le else if par un if

            //Là j'initialise l'image comme il faut mais on peux en faire un personalisé dans les conditions au dessus

            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            animauxVue.put(animal, imageView);
            carte.getChildren().add(imageView);
        }

        //On défini la position de l'image dans la carte
        imageView.setTranslateX(animal.getX() - imageView.getFitWidth() / 2);
        imageView.setTranslateY(animal.getY() - imageView.getFitWidth() / 2);
    }

    private void cleanupViews() {

        //J'ai mis un Iterator car il permet de delet sans avoir de prb d'index
        Iterator<Animaux> iterator = animauxManager.getAnimaux().iterator();

        while (iterator.hasNext()) {

            Animaux a = iterator.next();    //Je le stocke car si on le save pas on peux pas l'use, car on passera au suivant si on remet iterator.next

            if (!a.isAlive()) {

                iterator.remove();
                carte.getChildren().remove(animauxVue.remove(a));
            }
        }
    }


}