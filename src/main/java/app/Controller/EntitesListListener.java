package app.Controller;

import app.Modele.Entites.Entite;
import app.Modele.GameWorld;
import app.Vue.EntiteVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

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
                /*Iterator<? extends Animaux> iSupp = c.getRemoved().iterator();
                while (iSupp.hasNext())  {
                    a = iSupp.next();*/
                for (Entite e: c.getRemoved()) {
                    /*for(int j=0;j<carte.getChildren().size();j++){
                        if(carte.getChildren().get(j).getId()==a.getId()) {
                            carte.getChildren().remove(j);
                            System.out.println("ajout dans list");


                    }*/
                    carte.getChildren().removeIf(node -> e.getId().equals(node.getId()));
                }

            }
            if (c.wasAdded()) {
                /*Iterator<? extends Animaux> iAdd = c.getAddedSubList().iterator();
                while (iAdd.hasNext()) {
                    a= iAdd.next();*/
                for (Entite e: c.getAddedSubList()) {
                    System.out.println("ajout dans list");
                    carte.getChildren().add(EntiteVue.appliquerBonneImage(e));
                }
            }
        }
    }
}
