package app.Controller;

import app.Modele.Entites.Animaux;
import app.Vue.EntiteVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class AnimauxListListener implements ListChangeListener<Animaux> {

    //J'ai enlevé la liste des Animaux car elle n'était pas utilisée.

    @FXML
    private Pane carte;

    public AnimauxListListener(Pane carte){
        this.carte = carte;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Animaux> c){

        while(c.next()) {
            if (c.wasRemoved())
                for (Animaux a: c.getRemoved()) {
                    System.out.println("Removed animaux: "+a.toString());
                    carte.getChildren().removeIf(node -> a.getId().equals(node.getId()));
                }

            if (c.wasAdded()) {
                for (Animaux a: c.getAddedSubList()) {
                    System.out.println("ajout dans list");
                    carte.getChildren().add(EntiteVue.appliquerBonneImage(a));
                }
            }
        }
    }
}
