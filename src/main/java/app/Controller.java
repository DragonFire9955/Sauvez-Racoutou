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
    @FXML private Pane carte;

    private Timeline gameLoop;
    private int temps;

    double[][] listDirections;
    double vitesse;
    boolean isArrivedNextPoint;
    int ind;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listDirections = new double[][]{
                {0,0},
                {0, 50},
                {50, 50},
                {50, 0},
                {100, 0},
                {100, 50},
                {150, 50},
                {150, 0},
                {200, 0},
                {200, 50},
                {250, 50},
                {250, 0},
        };
        ind = 0;
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
                        vaVers(temps);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void vaVers(double dt) {

        double dx;
        double dy;
        if (isArrivedNextPoint) {
            dx = listDirections[ind][0] - ennemi1.getTranslateX();
            dy = listDirections[ind][1] - ennemi1.getTranslateY();
        } else {
            dx = listDirections[ind][0] - ennemi1.getTranslateX();
            dy = listDirections[ind][1] - ennemi1.getTranslateY();
        }
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 2) {
            isArrivedNextPoint = !isArrivedNextPoint;
            ind++;
            if (ind == listDirections.length) ind = 0;
            return;
        }

        dx /= dist;
        dy /= dist;

        ennemi1.setTranslateX(ennemi1.getTranslateX() + dx * vitesse);
        ennemi1.setTranslateY(ennemi1.getTranslateY() + dy * vitesse);
    }
}