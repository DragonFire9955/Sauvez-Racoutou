package app.Managers;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.transform.Scale;

public class CameraManager {

    private final Pane gamePane;
    private final Pane carte;
    private final TilePane tileMap;

    private final Scale scale;

    private final int tilesVisiblesX = 40;
    private final int tilesVisiblesY = 23;

    private final double zoomMin = 1;
    private final double zoomMax = 2.5;

    private final double vitesseCamera = 16;

    public CameraManager(Pane gamePane, Pane carte, TilePane tileMap) {

        this.gamePane = gamePane;
        this.carte = carte;
        this.tileMap = tileMap;

        scale = new Scale();

        scale.setPivotX(0);
        scale.setPivotY(0);

        scale.setX(1);
        scale.setY(1);

        carte.getTransforms().add(scale);
    }

    public void initialiserCamera() {

        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            //Je check tjr si la scène est pas encore définie pour éviter les bugs
            if (newScene != null) {
                //Je fais ça pour attendre que le chargement visuel s'initialise bien
                Platform.runLater(() -> {

                    ajusterZoomInitial();
                    centrerCarte();
                    verifierLimitesCamera();
                });

                initialiserControles();
            }
        });
    }

    private void initialiserControles() {

        //Manière d'écrire proposée par Java, car il n'y a que 1 action. Plus court et compréhensible.
        gamePane.getScene().setOnKeyPressed(this::deplacementClavier);

        gamePane.getScene().setOnScroll(event -> {

            //Si on zoom on agrandis (1.1) sinon on dégrandit (0.9), on juge pas les mots XD
            double zoom = event.getDeltaY() > 0 ? 1.1 : 0.9;

            appliquerZoom(zoom, event.getX(), event.getY());
        });
    }

    private void deplacementClavier(KeyEvent event) {

        switch (event.getCode()) {
            case LEFT, Q:
                carte.setTranslateX(carte.getTranslateX() + vitesseCamera);
                break;
            case RIGHT, D:
                carte.setTranslateX(carte.getTranslateX() - vitesseCamera);
                break;
            case UP, Z:
                carte.setTranslateY(carte.getTranslateY() + vitesseCamera);
                break;
            case DOWN, S:
                carte.setTranslateY(carte.getTranslateY() - vitesseCamera);
                break;
        }

        verifierLimitesCamera();
    }

    public void appliquerZoom(double multiplicateur, double sourisX, double sourisY) {

        double nouveauZoom = scale.getX() * multiplicateur;

        if (nouveauZoom < zoomMin)
            nouveauZoom = zoomMin;

        if (nouveauZoom > zoomMax)
            nouveauZoom = zoomMax;

        //Position de la souris avant le zoom
        double sourisDansCarteX = (sourisX - carte.getTranslateX()) / scale.getX();
        double sourisDansCarteY = (sourisY - carte.getTranslateY()) / scale.getX();

        //On fait le zoom
        scale.setX(nouveauZoom);
        scale.setY(nouveauZoom);

        //On met la carte au bon endroit (donc où on a zoomé)
        carte.setTranslateX(sourisX - sourisDansCarteX * nouveauZoom);
        carte.setTranslateY(sourisY - sourisDansCarteY * nouveauZoom);

        verifierLimitesCamera();
    }

    private void ajusterZoomInitial() {

        double zoom = Math.min(
                gamePane.getWidth() / (tilesVisiblesX * tileMap.getPrefTileWidth()),
                gamePane.getHeight() / (tilesVisiblesY * tileMap.getPrefTileHeight())
        );

        zoom = Math.max(zoomMin, Math.min(zoom, zoomMax));

        scale.setX(zoom);
        scale.setY(zoom);
    }

    public void centrerCarte() {

        carte.setTranslateX((gamePane.getWidth() - getMapWidth()) / 2);     //Centre horizontal
        carte.setTranslateY((gamePane.getHeight() - getMapHeight()) / 2);   //Centre vertical
    }

    public void verifierLimitesCamera() {

        //En gros si la map est plus petite que l'écran (pour x raisons) on garde le centrage.
        if (getMapWidth() <= gamePane.getWidth()) {
            carte.setTranslateX((gamePane.getWidth()-getMapWidth())/2);
        } else {
            if (carte.getTranslateX() > 0)
                carte.setTranslateX(0);

            if (carte.getTranslateX() < gamePane.getWidth()-getMapWidth())    //Si inférieur au minimum en X
                carte.setTranslateX(gamePane.getWidth()-getMapWidth());
        }

        if (getMapHeight() <= gamePane.getHeight()) {
            carte.setTranslateY((gamePane.getHeight()-getMapHeight())/2);
        } else {

            if (carte.getTranslateY() > 0) {
                carte.setTranslateY(0);
            }

            if (carte.getTranslateY() <  gamePane.getHeight()-getMapHeight()) {   //Si inférieur au minimum en Y
                carte.setTranslateY( gamePane.getHeight()-getMapHeight());
            }
        }
    }

    public double getMapWidth() {
        return tileMap.getPrefColumns() * tileMap.getPrefTileWidth() * scale.getX();
    }

    public double getMapHeight() {
        return tileMap.getPrefRows() * tileMap.getPrefTileHeight() * scale.getY();
    }

    public Scale getScale() {
        return scale;
    }
}