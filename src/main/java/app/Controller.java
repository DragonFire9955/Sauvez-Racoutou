package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Rectangle ennemi1;
    @FXML private Rectangle player;
    @FXML private Pane carte;

    private Timeline gameLoop;
    private int temps;

    double vitesse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vitesse = 2;

        initAnimation();
        // demarre l'animation
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.0017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    /*if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }*/
                    /*else*/if (temps%5==0){
                        vaVers();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void vaVers() {

        double dx = player.getLayoutX() - ennemi1.getLayoutX();
        double dy = player.getLayoutY() - ennemi1.getLayoutY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {return;}

        dx /= dist;
        dy /= dist;

        ennemi1.setLayoutX(ennemi1.getLayoutX() + dx * vitesse);
        ennemi1.setLayoutY(ennemi1.getLayoutY() + dy * vitesse);
    }
}