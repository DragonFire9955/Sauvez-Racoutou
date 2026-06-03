package app.Controller;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.Buffer.PouletConservateur;
import app.Modele.Entites.Animaux.Specialise.Debuffer.PouletIGPN;
import app.Modele.Entites.Animaux.Specialise.Debuffer.Stunner.PouletMenotte;
import app.Modele.Entites.Animaux.Specialise.PouletBouclier;
import app.Modele.Entites.Animaux.Volants.PouletVolant;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
import app.Vue.VieVue;
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

                    //créé la barre de cie et récupère son conteneur
                    VieVue barreVie = new VieVue(e);
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
