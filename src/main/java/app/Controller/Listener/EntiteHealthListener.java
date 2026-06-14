package app.Controller.Listener;

import app.Modele.Entites.Animaux.Racoutou;
import app.Modele.Entites.Entite;
import app.Vue.EntiteVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class EntiteHealthListener implements ChangeListener<Number> {

    @FXML private Pane carte;

    private Entite e;

    public EntiteHealthListener(Pane carte, Entite e) {

        this.carte = carte;
        this.e = e;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldV, Number newV) {

        if (newV.doubleValue() < oldV.doubleValue()) {
            Node entite = carte.lookup("#" + e.getId());
            if( !((ImageView) entite).getImage().getUrl().endsWith("degat.gif")) {
                ((ImageView) entite).setImage(EntiteVue.appliquerBonneImageGif(e));

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ((ImageView) entite).setImage(EntiteVue.appliquerBonneImage(e, false).getImage());
                    }
                }, 900);
            }
        } else if (newV.doubleValue() > oldV.doubleValue()) {
            Node entite = carte.lookup("#" + e.getId());
            if( !((ImageView) entite).getImage().getUrl().endsWith("soin.gif")) {
                ((ImageView) entite).setImage(EntiteVue.appliquerImageSoin(e));

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ((ImageView) entite).setImage(EntiteVue.appliquerBonneImage(e, false).getImage());
                    }
                }, 1000);
            }
        }
    }
}
