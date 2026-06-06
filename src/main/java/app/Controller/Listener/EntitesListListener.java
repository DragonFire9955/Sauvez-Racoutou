package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
import app.Controller.VieControlleur;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class EntitesListListener implements ListChangeListener<Entite> {

    //J'ai enlevé la liste des Animaux car elle n'était pas utilisée.

    @FXML
    private Pane carte;

    private GameWorld gameWorld;

    public EntitesListListener(Pane carte,  GameWorld gameWorld) {

        this.carte = carte;
        this.gameWorld = gameWorld;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Entite> c){

        while(c.next()) {
            if (c.wasRemoved()) {
                for (Entite e: c.getRemoved()) {

                    //On add les coins si c un ennemi
                    if (e instanceof Animal && !((Animal) e).isAllie())
                        gameWorld.setTotalCoin(gameWorld.getTotalCoin().getValue()+e.getCoin());

                    //On suppr du visuel
                    carte.getChildren().removeIf(node -> e.getId().equals(node.getId()));
                }

            }
            if (c.wasAdded()) {
                //parcours les entités ajoutés
                for (Entite e: c.getAddedSubList()) {
                    System.out.println("ajout dans list");

                    //affiche l'image de l'entite sur la carte
                    carte.getChildren().add(EntiteVue.appliquerBonneImage(e));

                    //Ajout d'un listener sur son état
                    e.getHealthProperty().addListener(new EntiteHealthListener(carte, e));

                    //créé la barre de vie et récupère son conteneur
                    VieControlleur barreVie = new VieControlleur(e);
                    StackPane visuelBarre = barreVie.getConteneur();

                    //associe l'id de la vie a l'entite pour les remove ensemble
                    visuelBarre.setId(e.getId());

                    //affiche la vie
                    carte.getChildren().add(visuelBarre);
                }
            }
        }
    }
}
