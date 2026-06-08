package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.StructureManager;
import app.Vue.TerrainVue;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleurDeClic implements EventHandler<MouseEvent> {

    private int idEntite;
    private boolean modePlacement;
    private GameWorld gameWorld;
    private TerrainVue terrainVue;
    private Pane carte;
    private Pane gamePane;

    public ControleurDeClic(int idEntite, boolean modePlacement, GameWorld gameWorld, TerrainVue terrainVue, Pane carte, Pane gamePane) {
        this.idEntite = idEntite;
        this.modePlacement = modePlacement;
        this.gameWorld = gameWorld;
        this.terrainVue = terrainVue;
        this.carte = carte;
        this.gamePane = gamePane;
    }

    @Override
    public void handle(MouseEvent event) {
        if (modePlacement) {
            ImageView tuileClique = (ImageView) event.getSource();
            //on prend la source pour avoir les coordonées de la case (et pas dans la case)

            double posX = tuileClique.getLayoutX();
            double posY = tuileClique.getLayoutY();

            //coordonnées en case
            int colonne = (int) (posX / gameWorld.getTailleTile());
            int ligne = (int) (posY / gameWorld.getTailleTile());

            placerStructure(ligne, colonne, idEntite);

            modePlacement = false;
        }
    }


    public void placerStructure(int ligne, int colonne, int idEntite) {
        int prixStructure = StructureManager.getPrix(idEntite);

        if (gameWorld.getMap()[ligne][colonne] == 0) { //si c'est du sol
            if (gameWorld.getTotalCoin().get() >= prixStructure) { //si on a assez d'argent

                //on déduit le prix
                gameWorld.getTotalCoin().set(gameWorld.getTotalCoin().get() - prixStructure);

                //pour avoir les coordonées absolues de la case
                double posX = colonne * gameWorld.getTailleTile();
                double posY = ligne * gameWorld.getTailleTile();
                double[] coords = new double[]{posX, posY};

                Entite entite = StructureManager.creerStructure(idEntite, coords, gameWorld);

                if (entite instanceof Barrage){
                    Barrage b = (Barrage) entite;
                    gameWorld.ajouterBarrage(b);
                    gameWorld.getMap()[ligne][colonne] = b.getPoids();

                    int id = b.getIdEntite();
                    ImageView imgStructure = terrainVue.creerTour(id);
                    imgStructure.setTranslateX(posX);
                    imgStructure.setTranslateY(posY);
                    carte.getChildren().add(imgStructure);

                } else if (entite instanceof Animal){
                    Animal a = (Animal) entite;
                    gameWorld.ajouterAnimal(a);
                }



                gamePane.requestFocus();
            }
        }
    }

    public void setIdEntite(int idEntite) {
        this.idEntite = idEntite;
    }

    public void setModePlacement(boolean modePlacement) {
        this.modePlacement = modePlacement;
    }


}