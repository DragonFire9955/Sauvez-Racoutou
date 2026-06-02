package app.Controller;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
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
                for (Entite e: c.getAddedSubList()) {
                    carte.getChildren().add(EntiteVue.appliquerBonneImage(e));

                    VieControlleur barreVie = new VieControlleur(e);
                    StackPane visuelBarre = barreVie.getConteneur();

                    visuelBarre.setId(e.getId());

                    carte.getChildren().add(visuelBarre);
                }
            }
        }
    }
}
