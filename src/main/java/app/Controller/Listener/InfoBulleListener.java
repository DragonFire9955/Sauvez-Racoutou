package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Animaux.Specialise.ChatHypnotiseur;
import app.Modele.Entites.Barrage.Barrage;
import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Modele.Managers.EntitesManager;
import app.Vue.EntiteVue;
import javafx.application.Platform;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class InfoBulleListener {

    private Pane carte;
    AnchorPane root;

    private GameWorld gameWorld;
    private Entite e;

    int qtiteRevente;

    private int actualTargetInt;

    public InfoBulleListener(Pane carte, GameWorld w, Entite e){

        this.carte = carte;
        root = new AnchorPane();

        this.gameWorld = w;
        this.e = e;

        qtiteRevente = e.getCoin()/2;

        actualTargetInt = 0;
    }

    public void ajoutZoneDescription() {

        root.setId("infoBulle"+e.getId());
        root.setPrefSize(265, 205);
        root.setStyle(
                "-fx-border-radius: 15;"+
                "-fx-border-style: none;"
        );
        setGoodStyle();

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

        root.getChildren().addAll(entityImageView, entityNameLabel);

        //Zone de sélection de cible
        if( e instanceof Animal) {
            List<String> target = new ArrayList<>();
            target.add("Strongest");
            target.add("Weakest");
            target.add("Nearest");

            actualTargetInt = target.size() - 1;

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

                if (actualTargetInt >= target.size())
                    actualTargetInt = 0;

                targetLabel.setText(target.get(actualTargetInt));
                ((Animal) e).setCibleInt(actualTargetInt);
            });

            Button nextButton = new Button(">");
            nextButton.setPrefSize(17, 17);
            nextButton.setFont(Font.font(7));
            nextButton.setOnMouseClicked(event -> {

                actualTargetInt--;

                if (actualTargetInt < 0)
                    actualTargetInt = target.size() - 1;

                targetLabel.setText(target.get(actualTargetInt));
                ((Animal) e).setCibleInt(actualTargetInt);
            });

            HBox targetBox = new HBox(5, previousButton, targetLabel, nextButton);
            targetBox.setLayoutX(14);
            targetBox.setLayoutY(110);

            root.getChildren().add(targetBox);
        }
        //Boutons close desc et sell
        Button closeButton = new Button("X");
        closeButton.setPrefSize(25, 25);
        closeButton.setOnMouseClicked(event -> {
            root.setVisible(false);
            carte.lookup("#perim"+e.getId()).setVisible(false);
            Node perimSpe = carte.lookup("#perimSpe"+e.getId());
            if (perimSpe != null)
                perimSpe.setVisible(false);

        });


        Button sellButton = new Button();
        sellButton.setPrefSize(65, 33);
        sellButton.setText("Sell : " + qtiteRevente);
        sellButton.setStyle(
                "-fx-border-style: 2;"+
                "-fx-background-color: #FFFCF2;"+
                "-fx-font-size: 12;" +
                "-fx-font-weight: bold;"+
                "-fx-background-radius: 10;"
                       // +"-fx-padding: -10;"
        );

        sellButton.setOnMouseClicked(event -> {
            gameWorld.setTotalCoin(gameWorld.getTotalCoin().getValue() + qtiteRevente);
            carte.getChildren().remove(root);
            e.setHealth(0);
        });

        HBox sellBox = new HBox(10, closeButton, sellButton);
        //Je met la marge haut au boutton de fermeture (Button n'a pas de margin alors je dois récup le parent pour lui appliquer ce margin)
        HBox.setMargin(closeButton,  new Insets(5, 0, 0, 0));
        HBox.setMargin(sellButton,  new Insets(0, 5, 0, 0));

        sellBox.setLayoutX(17);
        sellBox.setLayoutY(150);

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

        Label levelLabel = new Label(e.getLevel()+1 + "/4");
        levelLabel.textProperty().bind(e.getLevelProperty().add(1).asString().concat("/4"));
        levelLabel.setAlignment(Pos.CENTER);
        levelLabel.setPrefWidth(111);
        levelLabel.setFont(Font.font(10));

        upgradeBox.getChildren().addAll(
                nameUpgrade,
                priceUpgrade,
                levelLabel
        );

        //Image upgrade
        ImageView upgradeImageView = new ImageView();
        if (e.getLevel() == 3) {

            buyUpgradeButton.setDisable(true);
            upgradeImageView.setImage(null);
            upgradeBox.setStyle("-fx-background-color: darkgrey");
        }else {
            upgradeImageView.setImage(new Image("app/images/"+ e.getName()+"/niv"+(e.getLevel()+1)+"/img.png"));

            upgradeImageView.setFitWidth(38);
            upgradeImageView.setFitHeight(38);
            upgradeImageView.setPreserveRatio(true);
            upgradeImageView.setLayoutX(133);
            upgradeImageView.setLayoutY(8);
            upgradeImageView.setMouseTransparent(true);

        }

        //On fait en sorte que le boutton d'achat soit clickable de partout
        upgradeBox.setMouseTransparent(true);

        //Et on ajoute tout ça au StackPane
        upgradePane.getChildren().addAll(
                buyUpgradeButton,
                upgradeBox
        );

        //Zone des attributs (où on voit les améliorations concrètes)
        VBox attributesVBox = new VBox();

        attributesVBox.setPadding(new Insets(5));


        updateDescriptionStatLabel(attributesVBox);

        ScrollPane attributesScrollPane = new ScrollPane(attributesVBox);
        attributesScrollPane.setPrefSize(127, 109);
        attributesScrollPane.setLayoutX(134);
        attributesScrollPane.setLayoutY(88);
        HBox.setMargin(attributesScrollPane,  new Insets(0, 0, 5, 0));
        attributesScrollPane.setStyle(
                "-fx-background-radius: 10;"
        );
        Platform.runLater(() -> attributesScrollPane.lookup(".viewport").setStyle(
                "-fx-background-radius: 10;"
        ));


        //Action du boutton pr améliorer
        buyUpgradeButton.setOnMouseClicked(event -> {
            if(e.incrementLevelPossible()) {
                e.incrementerLevel();

                if (e.getLevel() == 3) {

                    buyUpgradeButton.setDisable(true);
                    upgradeImageView.setImage(null);
                    upgradeBox.setStyle("-fx-background-color: darkgrey");
                } else {

                    updateDescriptionStatLabel(attributesVBox);
                    updateDescriptionButtonUpgrade(nameUpgrade, priceUpgrade);
                    updateDescriptionSellButton(sellButton);

                    upgradeImageView.setImage(new Image("app/images/" + e.getName() + "/niv" + (e.getLevel() + 1) + "/img.png"));
                }

                entityImageView.setImage(new Image("app/images/" + e.getName() + "/niv" + e.getLevel() + "/img.png"));
            }
        });



        root.getChildren().addAll(
                sellBox,
                upgradePane,
                upgradeImageView,
                attributesScrollPane
        );

        //Je le cache pour l'instant, il sera affiché quand je cliquerais sur la troupe
        root.setVisible(false);

        carte.getChildren().addAll(root);
    }

    public void setGoodStyle() {

        if (e.getName().equals("poubelle"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #E53935, #E35D5B); -fx-text-fill: white; -fx-background-radius: 12;-fx-cursor: hand;" +
                        //"-fx-border-color: black;" +
                        "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("racoutou"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #ff3d86, #b258ff, #55eaff, #7AFA70, #DCFA70, #FCB045, #FF5454); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            "-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatClassique"))
            root.setStyle(
                    //"fx-background-color: linear-gradient(to bottom right, #FF9D00, #F2FF00, #FFF9C2); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;"+
                   "-fx-background-color: linear-gradient(to bottom right, #FF9D00, #F2FF00); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatCuisinier"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #00C9A7, #005F73); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                    //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatMedecin"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #FF8C00, #F12711); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("pouletIGPN"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #11998E, #38EF7D); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatScientifique"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #00C6FF, #0072FF); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatHypnotiseur"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #6A11CB, #2575FC); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
        else if (e.getName().equals("chatJournaliste"))
            root.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, #FF007F, #7928CA); -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;" +
                            //"-fx-border-color: black;" +
                            "-fx-border-radius: 2;"
            );
    }

    public void changeAfficherDescription() {

        root.setLayoutX(e.getX()+20);
        root.setLayoutY(e.getY()-200);
        root.toFront();
        root.setVisible(!root.isVisible());
    }

    private void updateDescriptionStatLabel(VBox attributesVBox) {

        if (e.getStatsLevels() != null && e.getStatsLevels().size() > 1 && e.getLevel() < e.getStatsLevels().size()-1) {        //A suppr quand j'aurais fait pr tt les Entites

            attributesVBox.getChildren().clear();

            for (int i = 0; i < e.getStatsLevels().get(e.getLevel()).length - 2; i++) {

                Object actualStat = e.getStatsLevels().get(e.getLevel())[i + 2];
                Object newStat = e.getStatsLevels().get((e.getLevel()) + 1)[i + 2];

                if (!actualStat.equals(newStat)) {

                    Label stats = new Label(getStatName(i)
                            + e.getStatsLevels().get(e.getLevel())[i + 2].toString()
                            + " → "
                            + e.getStatsLevels().get((e.getLevel()) + 1)[i + 2].toString());

                    stats.setStyle(
                            "-fx-background-color: none;"
                    );



                    attributesVBox.getChildren().add(stats);
                }
            }
        }
        if (e.getLevel() >= e.getStatsLevels().size()-1) {
            attributesVBox.getChildren().clear();
            attributesVBox.getChildren().add(new Label("Maxed out"));
        }
    }
    public String getStatName(int indiceStat) {

        String statName;
        switch (indiceStat) {
            case 0:
                statName = "Health : ";
                break;
            case 1:
                statName = "Range : ";
                break;
            case 2:
                statName = "Damage : ";
                break;
            case 3:
                statName = "FreqAtk : ";
                break;
            case 4:
                statName = "Speed : ";
                break;
            case 5:
                if (e instanceof ChatHypnotiseur)
                    statName = "Range spe : ";
                else
                    statName = "RangeEffect : ";
                break;
            case 6:
                if (e instanceof ChatHypnotiseur)
                    statName = "Dmg spe : ";
                else
                    statName = "Tps Buff : ";
                break;
            case 7:
                if (e instanceof ChatHypnotiseur)
                    statName = "Freq atk spe : ";
                else
                    statName = "Tps Repos : ";
                break;
            case 8:
                statName = "Nbr Victimes : ";
                break;
            case 9:
                statName = "Div force : ";
                break;
            case 10:
                statName = "Div vit : ";
                break;
            default:
                statName = "inconnu : ";
                break;
        }

        return statName;
    }

    private void updateDescriptionButtonUpgrade(Label nameUpgrade, Label priceUpgrade) {

        if (e.getLevel() < e.getStatsLevels().size()-1 && e.getStatsLevels().get(e.getLevel()+1)[0] != null) {

            nameUpgrade.setText(e.getStatsLevels().get(e.getLevel()+1)[0].toString());
            priceUpgrade.setText((e.getStatsLevels().get(e.getLevel())[1]).toString());
        }
    }

    private void updateDescriptionSellButton(Button sellButton) {

        qtiteRevente = (int) (e.getStatsLevels().get(e.getLevel())[1]) / 2;
        sellButton.setText("Sell : " + qtiteRevente);

    }
}
