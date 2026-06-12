package app.Controller.Listener;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EnnemisSpawn;
import app.Vue.EntiteVue;
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

public class InfoBulleListener {

    private Pane carte;

    private GameWorld gameWorld;
    private Entite e;

    private final Map<String, Node> descriptionsMap;

    private int actualLevel;
    int qtiteRevente;

    private int actualTargetInt;

    public InfoBulleListener(Pane carte, GameWorld w, Entite e){

        this.carte = carte;

        this.gameWorld = w;
        this.e = e;

        this.descriptionsMap = new HashMap<>();

        actualLevel = 0;
        qtiteRevente = e.getCoin()/2;

        actualTargetInt = 0;
    }

    public void ajoutZoneDescription() {

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

        List<String> target = new ArrayList<>();
        target.add("Strongest");
        target.add("Weakest");
        target.add("Farthest");
        target.add("Nearest");

        actualTargetInt = target.size()-1;

        Label targetLabel = new Label(target.getLast());
        targetLabel.setAlignment(Pos.CENTER);
        targetLabel.setPrefSize(67, 26);
        targetLabel.setStyle("-fx-background-color: rgba(0,0,0,0.3);");
        targetLabel.setTextFill(Color.WHITE);
        targetLabel.setFont(Font.font(10));

        Button previousButton = new Button("<");
        previousButton.setPrefSize(17, 17);
        previousButton.setFont(Font.font(7));
        previousButton.setOnMouseClicked(event -> {

            actualTargetInt++;

            if(actualTargetInt >= target.size())
                actualTargetInt = 0;

            targetLabel.setText(target.get(actualTargetInt));

        });

        Button nextButton = new Button(">");
        nextButton.setPrefSize(17, 17);
        nextButton.setFont(Font.font(7));
        nextButton.setOnMouseClicked(event -> {

            actualTargetInt--;

            if(actualTargetInt < 0)
                actualTargetInt = target.size()-1;

            targetLabel.setText(target.get(actualTargetInt));

        });

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
            gameWorld.setTotalCoin(gameWorld.getTotalCoin().getValue() + qtiteRevente);
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
                new Image("app/images/"+ e.getName()+"/niv"+(e.getLevel()+1)+"/img.png")
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

        updateDescriptionStatLabel(attributesVBox);

        ScrollPane attributesScrollPane = new ScrollPane(attributesVBox);
        attributesScrollPane.setPrefSize(127, 109);
        attributesScrollPane.setLayoutX(134);
        attributesScrollPane.setLayoutY(88);


        //Action du boutton pr améliorer
        buyUpgradeButton.setOnMouseClicked(event -> {

            e.incrementerLevel();

            //Partie FXML
            entityImageView.setImage(new Image("app/images/"+ e.getName()+"/niv"+e.getLevel()+"/img.png"));

            updateDescriptionStatLabel(attributesVBox);
            updateDescriptionButtonUpgrade(nameUpgrade, priceUpgrade);
            updateDescriptionSellButton(sellButton);

            upgradeImageView.setImage(new Image("app/images/"+ e.getName()+"/niv"+(e.getLevel()+1)+"/img.png"));
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

    public void afficherDescription() {

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

    private void updateDescriptionStatLabel(VBox attributesVBox) {

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

    private void updateDescriptionButtonUpgrade(Label nameUpgrade, Label priceUpgrade) {

        if (actualLevel < e.getStatsLevels().size()-1 && e.getStatsLevels().get(actualLevel+1)[0] != null) {

            nameUpgrade.setText(e.getStatsLevels().get(actualLevel+1)[0].toString());
            priceUpgrade.setText((e.getStatsLevels().get(actualLevel)[1]).toString());
        }
    }

    private void updateDescriptionSellButton(Button sellButton) {

        qtiteRevente = (int) (e.getStatsLevels().get(actualLevel)[1]) / 2;
        sellButton.setText("Sell : " + qtiteRevente);
    }
}
