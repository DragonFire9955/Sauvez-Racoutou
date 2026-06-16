package app.Controller.Listener;

import app.Modele.Entites.Animaux.Animal;
import app.Modele.Entites.Entite;
import app.Vue.EntiteVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        Node entite = carte.lookup("#" + e.getId());
        System.out.println(((ImageView) entite).getImage().getUrl());

        if (newV.doubleValue() < oldV.doubleValue()) {
            if( !((ImageView) entite).getImage().getUrl().endsWith("degat.gif")) {
                EntiteVue.appliquerBonneImageDegat(e, (ImageView) entite);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(e.getName());
                        System.out.println(((Animal) e).getHypnoProperty().getValue());
                            EntiteVue.appliquerBonneImage(e, (ImageView) entite);
                    }
                }, 500);
            }
        } else if (newV.doubleValue() > oldV.doubleValue()) {
            if( !((ImageView) entite).getImage().getUrl().endsWith("soin.gif")) {
                EntiteVue.appliquerImageSoin(e, (ImageView) entite);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        EntiteVue.appliquerBonneImage(e, (ImageView) entite);
                    }
                }, 500);
            }
        }
    }
}
