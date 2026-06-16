package app.Controller.Listener;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EntitesManager;
import app.Modele.Utilitaires.StatsEntiteInitialiser;
import app.Vue.TerrainVue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControleurDeClic implements EventHandler<MouseEvent> {

    private final GameWorld gameWorld;
    private final Pane carte;
    private final Pane gamePane;
    private final Rectangle highlight;

    private String name;

    //entite occupe taille * taille tiles
    private int tailleMaxEntite;

    private boolean modePlacement = false;

    public ControleurDeClic(Pane carte, Pane gamePane, GameWorld gameWorld, TerrainVue terrainVue) {
        this.carte = carte;
        this.gamePane = gamePane;
        this.gameWorld = gameWorld;

        this.tailleMaxEntite = 1;

        this.highlight = new Rectangle(gameWorld.getTailleTile(), gameWorld.getTailleTile());
        /*
        this.highlight.setFill(Color.rgb(255, 255, 0, 0.30));
        this.highlight.setStroke(Color.GOLD);

         */
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

        updateHighlight(e);
        highlight.setVisible(true);

        if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY) {

            //coordonées en case
            int colonne = (int) (e.getX() / gameWorld.getTailleTile());
            int ligne = (int) (e.getY() / gameWorld.getTailleTile());

            placerStructure(ligne, colonne, name);

            setModePlacement(false);

            e.consume();
        }
    }

    private void updateHighlight(MouseEvent e) {
        int tilesSize = gameWorld.getTailleTile();
        int colonne = (int) (e.getX() / tilesSize);
        int ligne = (int) (e.getY() / tilesSize);

        System.out.println(name + " taille " + tailleMaxEntite);

        if (colonne < 0 || ligne < 0) {
            highlight.setVisible(false);
            return;
        }

        highlight.setHeight(tailleMaxEntite * gameWorld.getTailleTile());
        highlight.setWidth(tailleMaxEntite * gameWorld.getTailleTile());

        if(!placementPossible(ligne, colonne, name)){
            System.out.println("PAS POSSIBLE " + name );
            this.highlight.setFill(Color.rgb(255, 64, 0, 0.30));
            this.highlight.setStroke(Color.RED);
        }
        else{
            this.highlight.setFill(Color.rgb(255, 255, 0, 0.30));
            this.highlight.setStroke(Color.GOLD);
        }


        highlight.setLayoutX(colonne * tilesSize);
        highlight.setLayoutY(ligne * tilesSize);
    }

    public boolean placementPossible(int ligne, int colonne, String nom){
        boolean possible;
        int prixStructure = (int) StatsEntiteInitialiser.getStatsLevels(nom).getFirst()[1];

        // Solde insuffisant
        if(gameWorld.getTotalCoin().get() < prixStructure)
            possible = false;

        // Espace suffisant (pour level max)
        else {
            possible = true;

            int i = 0, j;
            while (possible && i < tailleMaxEntite && ligne + i < gameWorld.getMap().length) {
                j = 0;
                while (possible && j < tailleMaxEntite && colonne + j < gameWorld.getMap()[i].length) {
                    if (gameWorld.getMap()[ligne + i][colonne + j] > 1)
                        possible = false;
                    j++;
                }
                i++;
            }
        }
        System.out.println(nom + " "+possible);
        return possible;
    }

    public void placerStructure(int ligne, int colonne, String nom) {

        if(placementPossible(ligne, colonne, nom)){
            System.out.println(nom + " placement possible");

            // on déduit le prix
            gameWorld.getTotalCoin().set(gameWorld.getTotalCoin().get() - (int) (StatsEntiteInitialiser.getStatsLevels(nom).getFirst()[1]));

            // pour avoir les coordonnées absolues de la case
            double posX = colonne * gameWorld.getTailleTile() + gameWorld.getTailleTile() * 0.5;
            double posY = ligne * gameWorld.getTailleTile() + gameWorld.getTailleTile() * 0.5;
            double[] coord = new double[]{posX, posY};

            EntitesManager.creerEntite(nom, coord, gameWorld);

            gamePane.requestFocus();
        }

    }



    public void setModePlacement(boolean modePlacement) {
        this.modePlacement = modePlacement;
        if (!modePlacement) {
            highlight.setVisible(false);
        }
    }

    public void setName(String name) {
        this.name = name;
        if(name.equals("poubelle"))
            tailleMaxEntite = ((int) StatsEntiteInitialiser.getStatsLevels("poubelle").get(3)[6]);
        else
            tailleMaxEntite = 1;
    }
}