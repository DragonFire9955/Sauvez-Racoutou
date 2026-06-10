package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.AnimauxManager;
import app.Vue.EntiteVue;
import app.Controller.VieControlleur;
import app.Vue.PerimetreVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
                    carte.getChildren().removeIf(node -> ("perim" + e.getId()).equals(node.getId()));
                }

            }
            if (c.wasAdded()) {
                //parcours les entités ajoutés
                for (Entite e: c.getAddedSubList()) {
                    System.out.println("ajout dans list");

                    //affiche l'image de l'entite sur la carte
                    Node imageEntite = EntiteVue.appliquerBonneImage(e, true);
                    if(e instanceof PouletIGPN)
                        carte.getChildren().add(1, PerimetreVue.initPerimetre((Specialise) e, (ImageView) imageEntite));
                    carte.getChildren().add(imageEntite);

                    //créé la barre de vie et récupère son conteneur
                    VieControlleur barreVie = new VieControlleur(e);
                    StackPane visuelBarre = barreVie.getConteneur();

                    //affiche la vie
                    carte.getChildren().add(visuelBarre);

                    //associe l'id de la vie a l'entite pour les remove ensemble
                    visuelBarre.setId(e.getId());
                    //Je met le listener de ma vie ici car + pratique et évite les bugs du lookup()
                    e.getHealthProperty().addListener(new EntiteHealthListener(carte, e));

                    //on lui crée sa description si c un allié
                    if (e instanceof Animal && ((Animal) e).isAllie()) {

                        InfoBulleListener infoBulleListener = new InfoBulleListener(carte, gameWorld, e);
                        infoBulleListener.ajoutZoneDescription();
                        imageEntite.setOnMouseClicked(event -> infoBulleListener.afficherDescription());
                    }
                }
            }
        }
    }
}
