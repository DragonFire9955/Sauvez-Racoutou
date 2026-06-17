package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Animaux.Specialise.Specialise;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
import app.Controller.VieControlleur;
import app.Vue.PerimetreVue;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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

        int i = 0;
        while(c.next()) {
            if (c.wasRemoved()) {
                for (Entite e: c.getRemoved()) {

                    //On add les coins si c un ennemi
                    if (e instanceof Animal && !((Animal) e).isAllie())
                        gameWorld.setTotalCoin(gameWorld.getTotalCoin().getValue()+e.getCoin());

                    //On suppr du visuel
                    carte.getChildren().removeIf(node -> e.getId().equals(node.getId()));
                    carte.getChildren().removeIf(node -> ("perim" + e.getId()).equals(node.getId()));
                    carte.getChildren().removeIf(node -> ("perimSpe" + e.getId()).equals(node.getId()));
                    carte.getChildren().removeIf(node -> ("visuBarre" + e.getId()).equals(node.getId()));
                    carte.getChildren().removeIf(node ->("infoBulle"+e.getId()).equals(node.getId()));
                }

            }

            if (c.wasAdded()) {

                //parcours les entités ajoutés
                for (Entite e: c.getAddedSubList()) {

                    //affiche l'image de l'entite sur la carte
                    ImageView imageEntite = EntiteVue.appliquerBonneImage(e, true);
                    carte.getChildren().add(imageEntite);


                    //Direction image
                    if(e instanceof Animal){

                        imageEntite.setScaleX(((Animal) e).getDirX());
                        ((Animal) e).getDirXProperty().addListener((observableValue, number, t1) -> {

                            ((ImageView) carte.lookup("#"+e.getId())).setScaleX(t1.doubleValue());
                        });

                        ((Animal) e).getHypno().addListener((observableValue, aBoolean, t1) -> {
                            if(t1)
                                ((ImageView) carte.lookup("#"+e.getId())).setImage(EntiteVue.appliquerImageHypno(e));
                        });
                    }

                    // Création périmètre(s)
                    PerimetreVue perim = new PerimetreVue(this.carte, e);
                    perim.initPerimetre(e, imageEntite);
                    perim.getP().radiusProperty().bind(e.getRangeProperty());
                    if(e instanceof Specialise){
                        perim.getpSpe().radiusProperty().bind(((Specialise) e).getRangeEffectProperty());
                    } else if (e.getName().equals("chatHypnotiseur")){
                        perim.getpSpe().radiusProperty().bind(((ChatHypnotiseur) e).getRangeSpeProperty());
                    }

                    //créé la barre de vie et récupère son conteneur
                    VieControlleur barreVie = new VieControlleur(e, imageEntite);
                    StackPane visuelBarre = barreVie.getConteneur();

                    //affiche la vie
                    carte.getChildren().add(visuelBarre);

                    //Je met le listener de ma vie ici car + pratique et évite les bugs du lookup()
                    e.getHealthProperty().addListener(new EntiteHealthListener(carte, e));

                    //Liaison niveau / image
                    e.getLevelProperty().addListener((observableValue, oldV, newV) -> {
                        EntiteVue.upgradeImage(e, ((ImageView) carte.lookup("#" + e.getId())));
                    });
                            //((ImageView) carte.lookup("#"+e.getId())).setImage(EntiteVue.appliquerBonneImage(e, true).getImage()));

                    //on lui crée sa description si c un allié
                    if (e instanceof Animal && ((Animal) e).isAllie() || e instanceof Barrage) {

                        InfoBulleListener infoBulleListener = new InfoBulleListener(carte, gameWorld, e);
                        infoBulleListener.ajoutZoneDescription();
                        imageEntite.setOnMouseClicked(event -> {
                            infoBulleListener.changeAfficherDescription();
                            perim.changeVisibility();
                        });
                    }
                }
            }
            i++;
        }
    }
}
