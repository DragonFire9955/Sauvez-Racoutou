package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.StructureManager;
import app.Vue.TerrainVue;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControleurDeClic implements EventHandler<MouseEvent> {

    private final GameWorld gameWorld;
    private final TerrainVue terrainVue;
    private final Pane carte;
    private final Pane gamePane;
    private final Rectangle highlight;

    private int idEntite = -1;
    private boolean modePlacement = false;

    public ControleurDeClic(Pane carte, Pane gamePane, GameWorld gameWorld, TerrainVue terrainVue) {
        this.carte = carte;
        this.gamePane = gamePane;
        this.gameWorld = gameWorld;
        this.terrainVue = terrainVue;

        this.highlight = new Rectangle(gameWorld.getTailleTile(), gameWorld.getTailleTile());
        this.highlight.setFill(Color.rgb(255, 255, 0, 0.30));
        this.highlight.setStroke(Color.GOLD);
        this.highlight.setStrokeWidth(2);

        this.highlight.setMouseTransparent(true);
        this.highlight.setVisible(false);

        this.carte.getChildren().add(highlight);
    }

    @Override
    public void handle(MouseEvent e) {

        //si clic droit alors on annule
        if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.SECONDARY) {
            setModePlacement(false);
            e.consume();
            return;
        }

        if (!modePlacement) {
            highlight.setVisible(false);
            return;
        }


        highlight.setVisible(true);
        updateHighlight(e);

        if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY) {

            //coordonées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            placerStructure(ligne, colonne, idEntite);

            setModePlacement(false);
            e.consume();
        }
    }

    private void updateHighlight(MouseEvent e) {
        int tileSize = gameWorld.getTailleTile();
        int col = (int) (e.getX() / tileSize);
        int row = (int) (e.getY() / tileSize);

        if (col < 0 || row < 0) {
            highlight.setVisible(false);
            return;
        }

        highlight.setLayoutX(col * tileSize);
        highlight.setLayoutY(row * tileSize);
    }

    public void placerStructure(int ligne, int colonne, int idEntite) {
        int prixStructure = StructureManager.getPrix(idEntite);

        if (gameWorld.getMap()[ligne][colonne] == 0) { // si c'est du sol
            if (gameWorld.getTotalCoin().get() >= prixStructure) { // si on a assez d'argent

                // on déduit le prix
                gameWorld.getTotalCoin().set(gameWorld.getTotalCoin().get() - prixStructure);

                // pour avoir les coordonnées absolues de la case
                double posX = colonne * gameWorld.getTailleTile();
                double posY = ligne * gameWorld.getTailleTile();
                double[] coords = new double[]{posX, posY};

                Entite entite = StructureManager.creerStructure(idEntite, coords, gameWorld);

                if (entite instanceof Barrage) {
                    Barrage b = (Barrage) entite;
                    gameWorld.ajouterBarrage(b);
                    gameWorld.getMap()[ligne][colonne] = b.getPoids();

                    int id = b.getIdEntite();
                    ImageView imgStructure = terrainVue.creerTour(id);
                    imgStructure.setTranslateX(posX);
                    imgStructure.setTranslateY(posY);
                    carte.getChildren().add(imgStructure);

                } else if (entite instanceof Animal) {
                    Animal a = (Animal) entite;
                    gameWorld.ajouterAnimal(a);
                }

                gamePane.requestFocus();
            }
        }
    }

    public void setModePlacement(boolean modePlacement) {
        this.modePlacement = modePlacement;
        if (!modePlacement) {
            highlight.setVisible(false);
        }
    }

    public void setIdEntite(int idEntite) {
        this.idEntite = idEntite;
    }
}