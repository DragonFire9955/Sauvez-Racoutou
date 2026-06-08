package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
import app.Controller.VieControlleur;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class EntitesListListener implements ListChangeListener<Entite> {

    //J'ai enlevé la liste des Animaux car elle n'était pas utilisée.

    @FXML
    private Pane carte;

    private GameWorld gameWorld;

    private final Map<String, Node> descriptionsMap = new HashMap<>();

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
                    Node imageEntite = EntiteVue.appliquerBonneImage(e, true);

                    carte.getChildren().add(imageEntite);

                    //on lui crée sa description si c un allié
                    if (e instanceof Animal && ((Animal) e).isAllie()) {

                        ajoutZoneDescription(e);
                        imageEntite.setOnMouseClicked(event -> afficherDescription(e));
                    }

                    //créé la barre de vie et récupère son conteneur
                    VieControlleur barreVie = new VieControlleur(e);
                    StackPane visuelBarre = barreVie.getConteneur();

                    //affiche la vie
                    carte.getChildren().add(visuelBarre);

                    //associe l'id de la vie a l'entite pour les remove ensemble
                    visuelBarre.setId(e.getId());
                    //Je met le listener de ma vie ici car + pratique et évite les bugs du lookup()
                    e.getHealthProperty().addListener(new EntiteHealthListener(carte, e));
                }
            }
        }
    }

    public void ajoutZoneDescription(Entite e) {

        AtomicInteger actualLevel = new AtomicInteger(0);   //Voir l'action de l'amélioration à la fin

        AtomicInteger qtiteRevente = new AtomicInteger((int) (e.getCoin()/2));   //On définit la quantité rendue

        AnchorPane root = new AnchorPane();
        root.setPrefSize(260, 200);
        root.setStyle(
                "-fx-background-color: rgb(196,196,196);" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 2;"
        );

        //Image principale
        ImageView entityImageView = EntiteVue.appliquerBonneImage(e, false);
        entityImageView.setFitWidth(109);
        entityImageView.setFitHeight(110);
        entityImageView.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(entityImageView, 14.0);
        AnchorPane.setTopAnchor(entityImageView, 14.0);

        //Nom de l'entité
        Label entityNameLabel = new Label(e.getName());
        entityNameLabel.setAlignment(Pos.CENTER);
        entityNameLabel.setPrefSize(109, 26);
        entityNameLabel.setTextFill(Color.WHITE);
        entityNameLabel.setStyle("-fx-background-color: rgba(0,0,0,0.3);");
        entityNameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        AnchorPane.setLeftAnchor(entityNameLabel, 14.0);
        AnchorPane.setTopAnchor(entityNameLabel, 14.0);

        //Zone de sélection de cible
        Button previousButton = new Button("<");
        previousButton.setPrefSize(17, 17);
        previousButton.setFont(Font.font(7));

        Label targetLabel = new Label("First enemy");
        targetLabel.setAlignment(Pos.CENTER);
        targetLabel.setPrefSize(67, 26);
        targetLabel.setStyle("-fx-background-color: rgba(0,0,0,0.3);");
        targetLabel.setTextFill(Color.WHITE);
        targetLabel.setFont(Font.font(10));

        Button nextButton = new Button(">");
        nextButton.setPrefSize(17, 17);
        nextButton.setFont(Font.font(7));

        HBox targetBox = new HBox(5, previousButton, targetLabel, nextButton);
        targetBox.setLayoutX(14);
        targetBox.setLayoutY(110);

        //Boutons close desc et sell
        Button closeButton = new Button("X");
        closeButton.setPrefSize(25, 25);
        closeButton.setOnMouseClicked(event ->
                root.setVisible(false)
        );


        Button sellButton = new Button();
        sellButton.setPrefSize(73, 35);
        sellButton.setText("Sell : " + qtiteRevente);
        sellButton.setOnMouseClicked(event -> {
            gameWorld.setTotalCoin(gameWorld.getTotalCoin().getValue() + qtiteRevente.get());
            carte.getChildren().remove(root);
            e.setHealth(0);
        });

        HBox sellBox = new HBox(5, closeButton, sellButton);
        //Je met la marge haut au boutton de fermeture (Button n'a pas de margin alors je dois récup le parent pour lui appliquer ce margin)
        HBox.setMargin(closeButton,  new Insets(5, 0, 0, 0));

        sellBox.setLayoutX(14);
        sellBox.setLayoutY(143);

        // Zone upgrade
        //On crée un StackPane qui va contenir le tout
        StackPane upgradePane = new StackPane();
        upgradePane.setLayoutX(143);
        upgradePane.setLayoutY(20);
        upgradePane.setPrefSize(109, 63);

        // Bouton d'achat
        Button buyUpgradeButton = new Button();
        buyUpgradeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buyUpgradeButton.setPrefSize(109, 63);

        buyUpgradeButton.setStyle(
                "-fx-border-color: rgba(50,50,50,1);" +
                        "-fx-background-color: rgb(217,217,217);" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;"
        );

        //On voyait pas le fait du click comme les autres (jsp pk) donc je l'ai créé manuellement
        buyUpgradeButton.setOnMousePressed(event ->
                buyUpgradeButton.setStyle(
                        "-fx-border-color: rgba(50,50,50,1);" +
                                "-fx-background-color: rgb(180,180,180);" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                )
        );
        buyUpgradeButton.setOnMouseReleased(event ->
                buyUpgradeButton.setStyle(
                        "-fx-border-color: rgba(50,50,50,1);" +
                                "-fx-background-color: rgb(217,217,217);" +
                                "-fx-border-radius: 5;" +
                                "-fx-background-radius: 5;"
                )
        );

        //Vbox contenant les textes
        VBox upgradeBox = new VBox();
        upgradeBox.setPrefSize(109, 63);

        Label nameUpgrade = new Label(e.getName());
        VBox.setMargin(nameUpgrade, new Insets(0, 0, 0, 40));

        Label priceUpgrade = new Label(Integer.toString((int) (e.getCoin())));

        priceUpgrade.setAlignment(Pos.CENTER);
        priceUpgrade.setPrefWidth(111);
        priceUpgrade.setFont(Font.font(16));

        Label levelLabel = new Label("0/4");
        levelLabel.setAlignment(Pos.CENTER);
        levelLabel.setPrefWidth(111);
        levelLabel.setFont(Font.font(10));

        upgradeBox.getChildren().addAll(
                nameUpgrade,
                priceUpgrade,
                levelLabel
        );

        //Image upgrade
        ImageView upgradeImageView = new ImageView(
                new Image("app/images/pouletIGPN.gif")
        );
        upgradeImageView.setFitWidth(38);
        upgradeImageView.setFitHeight(38);
        upgradeImageView.setPreserveRatio(true);
        upgradeImageView.setLayoutX(133);
        upgradeImageView.setLayoutY(8);

        //On fait en sorte que le boutton d'achat soit clickable de partout
        upgradeBox.setMouseTransparent(true);
        upgradeImageView.setMouseTransparent(true);

        //Et on ajoute tout ça au StackPane
        upgradePane.getChildren().addAll(
                buyUpgradeButton,
                upgradeBox
        );

        //Zone des attributs (où on voit les améliorations concrètes)
        VBox attributesVBox = new VBox();
        attributesVBox.setPadding(new Insets(2));

        updateDescriptionStatLabel(e, actualLevel.get(), attributesVBox);

        ScrollPane attributesScrollPane = new ScrollPane(attributesVBox);
        attributesScrollPane.setPrefSize(127, 109);
        attributesScrollPane.setLayoutX(134);
        attributesScrollPane.setLayoutY(88);


        //Action du boutton pr améliorer
        buyUpgradeButton.setOnMouseClicked(event -> {

            actualLevel.getAndIncrement();
            updateDescriptionStatLabel(e, actualLevel.get(), attributesVBox);
            updateDescriptionButtonUpgrade(e, actualLevel.get(), nameUpgrade, priceUpgrade);
            updateDescriptionSellButton(e, actualLevel.get(), qtiteRevente, sellButton);
        });



        root.getChildren().addAll(
                entityImageView,
                entityNameLabel,
                targetBox,
                sellBox,
                upgradePane,
                upgradeImageView,
                attributesScrollPane
        );

        //Je le cache pour l'instant, il sera affiché quand je cliquerais sur la troupe
        root.setVisible(false);

        //je l'add à la map
        descriptionsMap.put(e.getId(), root);

        carte.getChildren().addAll(root);
    }

    private void afficherDescription(Entite e) {

        descriptionsMap.values().forEach(n -> n.setVisible(false));

        Node desc = descriptionsMap.get(e.getId());

        if (desc != null) {
            //repositionne au bon endroit
            desc.setLayoutX(e.getX()+20);
            desc.setLayoutY(e.getY()-200);
            desc.setVisible(true);
            desc.toFront();
        }
    }

    private void updateDescriptionStatLabel(Entite e, int actualLevel, VBox attributesVBox) {

        if (e.getStatsLevels() != null && e.getStatsLevels().size() > 1 && actualLevel < e.getStatsLevels().size()-1) {        //A suppr quand j'aurais fait pr tt les Entites

            attributesVBox.getChildren().clear();

            for (int i = 0; i < e.getStatsLevels().get(actualLevel).length - 2; i++) {

                Object actualStat = e.getStatsLevels().get(actualLevel)[i + 2];
                Object newStat = e.getStatsLevels().get((actualLevel) + 1)[i + 2];

                if (!actualStat.equals(newStat))
                    attributesVBox.getChildren().add(new Label(
                            e.getStatsLevels().get(actualLevel)[i + 2].toString()
                                    + " -> "
                                    + e.getStatsLevels().get((actualLevel) + 1)[i + 2].toString()
                    ));
            }
        }
        if (actualLevel >= e.getStatsLevels().size()-1) {
            attributesVBox.getChildren().clear();
            attributesVBox.getChildren().add(new Label("Maxed out"));
        }
    }

    private void updateDescriptionButtonUpgrade(Entite e, int actualLevel, Label nameUpgrade, Label priceUpgrade) {

        if (actualLevel < e.getStatsLevels().size()-1 && e.getStatsLevels().get(actualLevel+1)[0] != null) {

            nameUpgrade.setText(e.getStatsLevels().get(actualLevel+1)[0].toString());
            priceUpgrade.setText((e.getStatsLevels().get(actualLevel)[1]).toString());
        }
    }

    private void updateDescriptionSellButton(Entite e, int actualLevel, AtomicInteger qtiteRevente, Button sellButton) {

        qtiteRevente.set((int) (e.getStatsLevels().get(actualLevel)[1]) / 2);
        sellButton.setText("Sell : " + qtiteRevente.get());
    }
}
